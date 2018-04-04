package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tuyen.novahub.assignment4.entity.BankAccount;

@Repository
public interface BankAccountDAO extends CrudRepository<BankAccount, Long>{
	List<BankAccount> findByFullName(String fullName);
 
}