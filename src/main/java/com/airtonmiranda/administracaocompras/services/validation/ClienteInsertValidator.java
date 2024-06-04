package com.airtonmiranda.administracaocompras.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.airtonmiranda.administracaocompras.controller.exception.FieldMessage;
import com.airtonmiranda.administracaocompras.dto.ClienteNewDTO;
import com.airtonmiranda.administracaocompras.entities.Cliente;
import com.airtonmiranda.administracaocompras.enums.TipoClienteE;
import com.airtonmiranda.administracaocompras.repositories.ClienteRepository;
import com.airtonmiranda.administracaocompras.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> lista = new ArrayList<>();
		
		if(clienteNewDTO.getTipoCliente().equals(TipoClienteE.PESSOA_FISICA.getCod()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(clienteNewDTO.getTipoCliente().equals(TipoClienteE.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(clienteNewDTO.getEmail());
		
		if(cliente != null) {
			lista.add(new FieldMessage("email", "E-mail já está em uso"));
		}
		
		for (FieldMessage fieldMessage : lista) {
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		return lista.isEmpty();
	}
	
}
