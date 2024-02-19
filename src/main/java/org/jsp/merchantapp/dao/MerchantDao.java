package org.jsp.merchantapp.dao;

import java.util.List;
import java.util.Optional;
import org.jsp.merchantapp.dto.Merchant;
import org.jsp.merchantapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	public boolean deleteById(int id) {
		Optional<Merchant> recMerchant = merchantRepository.findById(id);
		if (recMerchant.isPresent()) {
			merchantRepository.delete(recMerchant.get());
			return true;
		} else {
			return false;
		}
	}

	public List<Merchant> findByName(String name) {
		return merchantRepository.verifyByName(name);
	}

	public Optional<Merchant> verifyByPhone(long phone, String password) {
		return merchantRepository.verify(phone, password);
	}

}
