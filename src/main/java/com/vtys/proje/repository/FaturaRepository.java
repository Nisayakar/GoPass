package com.vtys.proje.repository;

import com.vtys.proje.entity.Fatura;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {
	List<Fatura> findByOdeme_OdemeId(Integer odemeId);
}
