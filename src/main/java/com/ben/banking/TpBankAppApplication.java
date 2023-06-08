package com.ben.banking;

import com.ben.banking.dtos.BankAccountDTO;
import com.ben.banking.dtos.CurrentBankAccountDTO;
import com.ben.banking.dtos.CustomerDTO;
import com.ben.banking.dtos.SavingBankAccountDTO;
import com.ben.banking.exceptions.BalanceNotSufficientException;
import com.ben.banking.exceptions.BankAccountNotFoundException;
import com.ben.banking.exceptions.CustomerNotFoundException;
import com.ben.banking.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TpBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpBankAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService accountService){
		return args -> {
			Stream.of("Hassan","Imane","Mohamed").forEach(name->{
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setName(name);
				customerDTO.setEmail(name+"@gmail.com");
				accountService.saveCustomer(customerDTO);
			});

			accountService.listCustomers().forEach(customerDTO -> {
				try {
					accountService.saveCurrentBankAccount(Math.random()*90000,9000,customerDTO.getId());
					accountService.saveSavingBankAccount(Math.random()*120000,5.5, customerDTO.getId());
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
			});

			List<BankAccountDTO> bankAccountDTOS = accountService.bankAccountList();
			bankAccountDTOS.stream().forEach(bankAccountDTO -> {
				for (int i=0; i<10; i++){
					String accountId;
					if(bankAccountDTO instanceof SavingBankAccountDTO){
						accountId = ((SavingBankAccountDTO) bankAccountDTO).getId();
					} else {
						accountId = ((CurrentBankAccountDTO) bankAccountDTO).getId();
					}
					try {
						accountService.credit(accountId,10000+Math.random()*120000,"Credit");
						accountService.debit(accountId,1000+Math.random()*9000,"Debit");
					} catch (BankAccountNotFoundException e) {
						e.printStackTrace();
					} catch (BalanceNotSufficientException e) {
						e.printStackTrace();
					}
				}
			});
		};
	}
}
