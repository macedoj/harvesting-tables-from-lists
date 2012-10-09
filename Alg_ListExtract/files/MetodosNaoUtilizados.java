
public class MetodosNaoUtilizados {

//===========================================================================
    //0921836735.341718
    int numValido = validNumericValue("0921836735.341718");

    System.out.println (
    "N�mero v�lido: " + numValido);

        //12:47
        int dateTime = validDateTime("25/08/2012");

    System.out.println (
    "Data v�lida: " + dateTime);

        // 0,33
        int currencyValue = validCurrencyValues("9.005,87");

    System.out.println (
    "Valor Monet�rio V�lido: " + currencyValue);

        //https://lol.net
        int urls = validURLs("http://cursos.unipampa.edu.br");

    System.out.println (
    "URL V�lida: " + urls);

        //meuemail@meudominio.com.br
        int e_mailValido = validEmail("j.rodovalho.m@gmail.com");

    System.out.println (
    "E-mail v�lido: " + e_mailValido);

        //(64)1234-5678
        int numPhone = numPhoneValid("(11)1111-1111");

    System.out.println (
    "V�lidar N�mero de Telefone: " + numPhone);

        //99999-9999 < Zip code EUA 
        int ceps = zipCodeValid("75709-030");

    System.out.println (

    "V�lidar CEPs: " + ceps);


        return 0;
    
   //======================================================================== 
    
    /**
     *
     * @param numValue
     * @return
     */
    public int validNumericValue(String numValue) {

        //Set the number value pattern string  
        Pattern p = Pattern.compile("^\\d*[0-9](\\.\\d*[0-9])?$");

        //Match the given string with the pattern  
        Matcher m = p.matcher(numValue);

        //check whether match is found   
        boolean matchFound = m.matches();
        
        if (matchFound) {
            
            return 1; // N�mero v�lido
        } else {
            return 0; // N�mero inv�lido
        }
    }
    
    public int validDateTime(String dateTime) {


        //Set the date or time pattern string  
        Pattern date = Pattern.compile("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])"
                + "\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|"
                + "((16|[2468][048]|[3579][26])00))))$");
        //Set the time pattern string
        Pattern time = Pattern.compile("^([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2}$");

        //Match the given string with the pattern  
        Matcher mDate = date.matcher(dateTime);
        Matcher mTime = time.matcher(dateTime);

        //check whether match is found   
        boolean matchFounDate = mDate.matches();
        boolean matchFoundTime = mTime.matches();
        
        if (matchFounDate || matchFoundTime) {
            
            return 1; //Data v�lido
        } else {
            return 0; // Data inv�lido
        }
        
    }
    
    public int validCurrencyValues(String currencyValue) {

        //Set the currency values pattern string  
        Pattern p = Pattern.compile("^(?:[1-9](?:[\\d]{0,2}(?:\\.[\\d]{3})*|[\\d]+)|0)(?:,[\\d]{0,2})?$");

        //Match the given string with the pattern  
        Matcher m = p.matcher(currencyValue);

        //check whether match is found   
        boolean matchFound = m.matches();
        
        if (matchFound) {
            
            return 1; // Currency values v�lido
        } else {
            return 0; // Currency values inv�lido
        }
    }
    
    private int validURLs(String url) {

        //Set the URLs pattern string                      Arrumar o final para que este aceite links com cadeias de p�ginas(home/projects/software/etc) .(/\\w)
        Pattern pURLs = Pattern.compile("^(http[s]?://|ftp://)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)$");

        //Match the given string with the pattern  
        Matcher mURLs = pURLs.matcher(url);

        //check whether match is found   
        boolean matchFoundURLs = mURLs.matches();
        
        if (matchFoundURLs) {
            
            return 1; // URLs v�lido
        } else {
            return 0; // URLs inv�lido
        }
        
    }

    /**
     *
     * @param email
     * @return 1 se o e-mail � v�lido e 0 caso contrario.
     */
    public int validEmail(String email) {

        //Set the email pattern string  
        Pattern pEmail = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3}$");

        //Match the given string with the pattern  
        Matcher mEmail = pEmail.matcher(email);

        //check whether match is found   
        boolean matchFoundEMAIL = mEmail.matches();
        
        if (matchFoundEMAIL) {
            
            return 1; //E-mail v�lido
        } else {
            return 0; // E-mail inv�lido
        }
    }
    
    private int numPhoneValid(String phoneNum) {

        //Set the phone number pattern string  
        Pattern pPhoneNum = Pattern.compile("^\\(?\\d{2}\\)?[\\s-]?\\d{4}-?\\d{4}$");

        //Match the given string with the pattern  
        Matcher mPNum = pPhoneNum.matcher(phoneNum);

        //check whether match is found   
        boolean matchFoundPNUM = mPNum.matches();
        
        if (matchFoundPNUM) {
            
            return 1; // Phone number v�lido
        } else {
            return 0; // Phone number inv�lido
        }
        
    }
    
    private int zipCodeValid(String ceps) {

        //Set the zip code Br or EUA pattern string  
        Pattern zipBR = Pattern.compile("^[0-9]{2}[0-9]{3}-[0-9]{3}$");
        Pattern zipEUA = Pattern.compile("\\d{5}(-\\d{4})?$");

        //Match the given string with the pattern  
        Matcher mZipBR = zipBR.matcher(ceps);
        Matcher mZipEua = zipEUA.matcher(ceps);

        //check whether match is found   
        boolean matchFoundBR = mZipBR.matches();
        boolean matchFoundEUA = mZipEua.matches();
        
        if (matchFoundBR || matchFoundEUA) {
            
            return 1; // Zip codev�lido
        } else {
            return 0; // Zip code inv�lido
        }
    }
    //===========================================================================    
    /**
     * Our implementation currently recognizes numeric values, datetime values,
     * currency values, URLs, emails, phone numbers, and zip codes.
     *
     * Referência E.R: http://tools.lymas.com.br/regexp_br.php
     *
     * @return 1 se o tipo é conhecido ou 0 se o tipo é desconhecido
     */
//    public int typeScore(String subSequences) {
//
//        //Set the number value pattern string  
//        Pattern pNumVal = Pattern.compile("^\\d*[0-9](\\.\\d*[0-9])?$");
//
//        Pattern pDate = Pattern.compile("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
//
//        Pattern pTime = Pattern.compile("^([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2}$");
//
//        Pattern pCurrencyValue = Pattern.compile("^(?:[1-9](?:[\\d]{0,2}(?:\\.[\\d]{3})*|[\\d]+)|0)(?:,[\\d]{0,2})?$");
//
//        Pattern pURLs = Pattern.compile("^(http[s]?://|ftp://)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)$");
//
//        Pattern pEmail = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3}$");
//
//        Pattern pPhoneNum = Pattern.compile("^\\(?\\d{2}\\)?[\\s-]?\\d{4}-?\\d{4}$");
//
//        Pattern pZipBR = Pattern.compile("^[0-9]{2}[0-9]{3}-[0-9]{3}$");
//        Pattern pZipEUA = Pattern.compile("\\d{5}(-\\d{4})?$");
//
//
//        //Match the given string with the pattern  
//        Matcher mNumVal = pNumVal.matcher(subSequences);
//
//        Matcher mDate = pDate.matcher(subSequences);
//        Matcher mTime = pTime.matcher(subSequences);
//
//        Matcher mCV = pCurrencyValue.matcher(subSequences);
//
//        Matcher mURLs = pURLs.matcher(subSequences);
//
//        Matcher mEmail = pEmail.matcher(subSequences);
//
//        Matcher mPNum = pPhoneNum.matcher(subSequences);
//
//        Matcher mZipBR = pZipBR.matcher(subSequences);
//        Matcher mZipEua = pZipEUA.matcher(subSequences);
//
//
//        //check whether match is found   
//        boolean matchFoundNV = mNumVal.matches();
//
//        boolean matchFounDate = mDate.matches();
//        boolean matchFoundTime = mTime.matches();
//
//        boolean matchFoundCV = mCV.matches();
//
//        boolean matchFoundURLs = mURLs.matches();
//
//        boolean matchFoundEMAIL = mEmail.matches();
//
//        boolean matchFoundPNUM = mPNum.matches();
//
//        boolean matchFoundBR = mZipBR.matches();
//        boolean matchFoundEUA = mZipEua.matches();
//
//        if (matchFoundNV || matchFounDate || matchFoundTime || matchFoundCV || matchFoundURLs || matchFoundEMAIL || matchFoundPNUM || matchFoundBR || matchFoundEUA) {
//
//            return 1; // Tipo de dado válido(Conhecido)
//        } else {
//            return 0; // Tipo de dado inválido(Desconhecido)
//        }
//
//
//    }
    //    public static void main(String[] args) {
//        /**
//         * Criar padrões que busquem dentro do patter da string qual parte é
//         * valida e retorna-la. Atribuir um score a cada retorno de pdrão(E.R)
//         * eX: SE FOR UM NÚMERO DE TELEFONE, este provavelmente é um campo,
//         * então deve ter um peso maior que um campo texto.
//         *
//         * Construir uma ER que identifique textos na linha, e atribuir o score
//         * baixo(em relação a um campo candidato ) para estes textos.
//         *
//         */
//        String teste = "09218367.35341718 jhggjhg 66678";
//        Pattern pNumVal = Pattern.compile("(\\d+(\\.\\d*)?)+");
//        Matcher mNumVal = pNumVal.matcher(teste);
//
//        while (mNumVal.find()) {
//
//            System.out.println(mNumVal.group());
//
//        }
//    }
}
