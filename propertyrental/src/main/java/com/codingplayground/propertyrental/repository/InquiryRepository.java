package com.codingplayground.propertyrental.repository;

import com.codingplayground.propertyrental.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
