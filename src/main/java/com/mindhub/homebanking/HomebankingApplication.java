package com.mindhub.homebanking;

import com.mindhub.homebanking.dtos.TurnDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TurnRepository turnRepository;

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(clientRepository clientRepository,AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
			Client admin = new Client("Admin", "Admin", "admin@admin.com", passwordEncoder.encode("admin123"));
			clientRepository.save(admin);
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode("1234"));
			clientRepository.save(client1);
			Client client2 = new Client("Matias", "Milich", "Mat210705@gmail.com",passwordEncoder.encode("1654"));
			clientRepository.save(client2);
			Client client3 = new Client("Rogelio", "Morel", "rogelio@minhub.com",passwordEncoder.encode("1947"));
			clientRepository.save(client3);
			Account cuenta1 = new Account("VIN001",AccountType.Ahorro, LocalDateTime.now(), 5000D);
			client1.addAccount(cuenta1);
			accountRepository.save(cuenta1);
			Account cuenta2 = new Account("VIN002",AccountType.Corriente, LocalDateTime.now().plusDays(1), 7500D);
			client1.addAccount(cuenta2);
			accountRepository.save(cuenta2);
			Account cuenta3 = new Account("VIN003",AccountType.Ahorro, LocalDateTime.now(), 2000D);
			client2.addAccount(cuenta3);
			accountRepository.save(cuenta3);
			Account cuenta4 = new Account("VIN004",AccountType.Ahorro, LocalDateTime.now(), 15000D);
			client3.addAccount(cuenta4);
			accountRepository.save(cuenta4);

			Transaction transaction1 = new Transaction(TransactionType.Credit, 50000, "Cobro Sueldo", LocalDateTime.now(), cuenta1, cuenta1.getBalance()+50000);
			transactionRepository.save(transaction1);
			cuenta1.addTransaction(transaction1);
			cuenta1.setBalance(cuenta1.getBalance()+ transaction1.getAmount());
			accountRepository.save(cuenta1);

			Transaction transaction2 = new Transaction(TransactionType.Debit, -2500, "Pago de Servicios", LocalDateTime.now(), cuenta1, cuenta1.getBalance()-2500);
			transactionRepository.save(transaction2);
			cuenta1.addTransaction(transaction2);
			cuenta1.setBalance(cuenta1.getBalance()-transaction2.getAmount());
			accountRepository.save(cuenta1);

			Transaction transaction3 = new Transaction(TransactionType.Debit, -1500, "Compra", LocalDateTime.now(), cuenta2, cuenta2.getBalance()-1500);
			transactionRepository.save(transaction3);
			cuenta2.addTransaction(transaction3);
			cuenta2.setBalance(cuenta2.getBalance() - transaction3.getAmount());
			accountRepository.save(cuenta2);

			Transaction transaction4 = new Transaction(TransactionType.Debit, -5000, "Pago de Impuestos", LocalDateTime.now(), cuenta3, cuenta3.getBalance()-5000);
			transactionRepository.save(transaction4);
			cuenta3.addTransaction(transaction4);
			cuenta3.setBalance(cuenta3.getBalance() - transaction4.getAmount());
			accountRepository.save(cuenta3);



			List<Integer> cuotas;
			Loan prestamo1 = new Loan("Hipotecario", 500000, 1.20, cuotas = List.of(12, 24, 48, 60));
			loanRepository.save(prestamo1);
			Loan prestamo2 = new Loan("Personal", 100000,1.25 , cuotas = List.of(6, 12, 24));
			loanRepository.save(prestamo2);
			Loan prestamo3 = new Loan("Automotriz",300000, 1.30, cuotas = List.of(6, 12, 24, 36));
			loanRepository.save(prestamo3);
			ClientLoan prestamoHipotecario = new ClientLoan(400000D * prestamo1.getPercentage(), 60, client1, prestamo1, cuenta1.getNumber());
			clientLoanRepository.save(prestamoHipotecario);
			ClientLoan prestamoPersonal = new ClientLoan(100000D * prestamo2.getPercentage(),  12, client1, prestamo2, "VIN002");
			clientLoanRepository.save(prestamoPersonal);
			ClientLoan prestamoAutomotriz = new ClientLoan(300000D * prestamo3.getPercentage(),  24, client2, prestamo3, "VIN003");
			clientLoanRepository.save(prestamoAutomotriz);
			Card tarjeta1 = new Card(01,client1.getFirstName()+ " " +client1.getLastName(), CardType.DEBIT, CardColor.GOLD, "1234"+"-"+"5678"+"-"+"9123"+"-"+"4567", 124, LocalDateTime.now(), LocalDateTime.now().plusYears(5), LocalDateTime.now(), client1);
			cardRepository.save(tarjeta1);
			Card tarjeta2 = new Card(02,client1.getFirstName()+ " " + client1.getLastName(), CardType.CREDIT, CardColor.TITANIUM, "9874"+"-"+"5632"+"-"+"1234"+"-"+"4569", 342, LocalDateTime.now(), LocalDateTime.now().plusYears(5), LocalDateTime.now(), client1);
			cardRepository.save(tarjeta2);
			Card tarjeta3 = new Card(03,client2.getFirstName()+ " " + client2.getLastName(), CardType.DEBIT, CardColor.SILVER, "9874518974445699", 3791, LocalDateTime.now(), LocalDateTime.now().plusYears(5), LocalDateTime.now(), client2);
			cardRepository.save(tarjeta3);
			Card tarjeta4 = new Card(04,client1.getFirstName()+" "+client1.getLastName(), CardType.CREDIT, CardColor.SILVER, "5987"+"-"+"2994"+"-"+"2391"+"-"+"6745", 357, LocalDateTime.of(2020,5,7,6,30,0), LocalDateTime.of(2021,5,7,6,30,0), LocalDateTime.now(),client1);
			cardRepository.save(tarjeta4);
			Turn turno1 = new Turn("Deposito", 0001, LocalDateTime.now(), LocalDateTime.of(2021, 11, 26, 06, 00, 00), "Compra de divisa", client1);
			turno1.getClient().addAccount(cuenta1);
			turnRepository.save(turno1);













			client1.addClientLoan(prestamoHipotecario);
			prestamo1.addClientLoan(prestamoHipotecario);
			client1.addClientLoan(prestamoPersonal);
			prestamo2.addClientLoan(prestamoPersonal);
			client2.addClientLoan(prestamoAutomotriz);
			prestamo3.addClientLoan(prestamoAutomotriz);



















		};
	}
}
