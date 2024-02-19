package org.jsp.merchantapp.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.sql.exec.spi.JdbcCallParameterExtractor;
import org.jsp.merchantapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select p from Product p where p.name=?1")
	public List<Product> findByName(String name);
}
