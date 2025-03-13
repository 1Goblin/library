package org.example.library.repository;


import java.util.List;
import java.util.Optional;
import org.example.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByBookIdAndReturnDateIsNull(Long bookId);

    Optional<List<Loan>> findByMemberIdAndReturnDateIsNull(Long memberId);

    Optional<List<Loan>> findByMemberId(Long memberId);

    Optional<Loan> findByMemberIdAndBookIdAndReturnDateIsNull(Long memberId, Long bookId);

}