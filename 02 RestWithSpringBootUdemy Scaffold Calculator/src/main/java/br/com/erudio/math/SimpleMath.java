package br.com.erudio.math;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.request.converter.NumberConverter;

@Service
public class SimpleMath {

	private NumberConverter validateMath= new NumberConverter();
	
	public Double sum(String numberOne,String numberTwo) throws Exception {
		
		validateNumber(numberOne);
		Double sum = validateMath.convertToDouble(numberOne) +validateMath.convertToDouble(numberTwo);
		return sum;
	}
	
	public Double sub(String numberOne,String numberTwo) throws Exception {
		
		validateNumber(numberOne);
		Double sum = validateMath.convertToDouble(numberOne) - validateMath.convertToDouble(numberTwo);
		return sum;
	}
	
	public Double mult(String numberOne,String numberTwo) throws Exception {
		
		validateNumber(numberOne);
		Double sum = validateMath.convertToDouble(numberOne) * validateMath.convertToDouble(numberTwo);
		return sum;
	}
	
	
	public Double div(String numberOne,String numberTwo) throws Exception {
		
		validateNumber(numberOne);
		Double sum = validateMath.convertToDouble(numberOne) / validateMath.convertToDouble(numberTwo);
		return sum;
	}
	
	public Double med(String numberOne,String numberTwo) throws Exception {
		
		validateNumber(numberOne);
		Double sum = (validateMath.convertToDouble(numberOne) + validateMath.convertToDouble(numberTwo))/2;
		return sum;
	}
	
	public Double sqrt(String numberOne) throws Exception {
		
		validateNumber(numberOne);
		Double sum = Math.sqrt(validateMath.convertToDouble(numberOne));
		return sum;
	}
	
	
	private void validateNumber(String number)  throws Exception{
		
		if(!validateMath.isNumeric(number)){
			throw new ResourceNotFoundException("Plase set a numeric value");
		}
	}
}
