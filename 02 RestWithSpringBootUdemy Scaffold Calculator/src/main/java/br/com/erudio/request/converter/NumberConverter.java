package br.com.erudio.request.converter;

public class NumberConverter {
	
	public Double convertToDouble(String strNumber) {
		// TODO Auto-generated method stub
		if(strNumber == null) 
			return 0D;
		
		String number = strNumber.replaceAll(",",".");
		if(isNumeric (number))
			return Double.parseDouble(number);
		
	    return 0D;
	}

	public boolean isNumeric(String strNumber) {
		// TODO Auto-generated method stub
		if(strNumber == null) 
			return false;
		String number = strNumber.replaceAll(",",".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
