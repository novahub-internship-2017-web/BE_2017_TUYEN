package tuyen.novahub.assignment4.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.entity.BankAccount;
import tuyen.novahub.assignment4.exception.MyException;
import tuyen.novahub.assignment4.service.BankAccountInfo;

@Repository
public class BankAccountDAO {
 
    @Autowired
    private EntityManager entityManager;
      
 
    public BankAccountDAO() {
    }
 
    public BankAccount findById(Long id) { 
        return this.entityManager.find(BankAccount.class, id);
    }
 
    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName() //
                + "(e.id,e.fullName,e.balance) " //
                + " from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }
 
    // MANDATORY: Bắt buộc phải có Transaction đã được tạo trước đó.
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, double amount) throws MyException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new MyException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new MyException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }
 
    // Không bắt ngoại lệ BankTransactionException trong phương thức này.
    @Transactional(propagation = Propagation.REQUIRES_NEW, 
                        rollbackFor = MyException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws MyException {
 
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
 
}