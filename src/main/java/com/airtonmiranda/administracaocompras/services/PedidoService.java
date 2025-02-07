package com.airtonmiranda.administracaocompras.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airtonmiranda.administracaocompras.entities.ItemPedido;
import com.airtonmiranda.administracaocompras.entities.PagamentoBoleto;
import com.airtonmiranda.administracaocompras.entities.Pedido;
import com.airtonmiranda.administracaocompras.enums.StatusPagamentoE;
import com.airtonmiranda.administracaocompras.repositories.ItemPedidoRepository;
import com.airtonmiranda.administracaocompras.repositories.PagamentoRepository;
import com.airtonmiranda.administracaocompras.repositories.PedidoRepository;
import com.airtonmiranda.administracaocompras.services.exception.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository; 
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido getById(Integer id){
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(()  -> new ObjectNotFoundException(
				"Pedido não encontrado"));
	}
	
	@Transactional
	public Pedido create(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(StatusPagamentoE.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagBoleto(pagto, obj.getInstante());	
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.getById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
