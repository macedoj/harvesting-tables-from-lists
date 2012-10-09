/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since 15/07/2012 - Last change: 05/09/2012
 * @version 0.1
 * @author Juliano R. Macedo
 *
 * Our implementation currently recognizes numeric values, datetime values,
 * currency values, URLs emails, phone numbers, and zip codes.
 *
 * Referência E.R: http://tools.lymas.com.br/regexp_br.php
 *
 * ATENÇÃO: QUANDO FOR REFATORAR, CENTRALIZAR OS ESFORÇOS NAS EXPRESSÕES
 * REGULARES, PARA DEIXA-LAS O MAIS DINÂMICAS E OBJETIVAS POSSIVEIS.
 *
 */
public class TypeScore {

    public TypeScore() {
    }

    /**
     * @param numValue
     * @return ArrayList contendo as pontuações e os campos candidatos
     * referentes
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList validNumericValue(String numValue) {

        ArrayList stringFound = new ArrayList<>();

        try {
            Pattern pNumVal = Pattern.compile("(\\d+(\\.\\d*)?)+");
            Matcher mNumVal = pNumVal.matcher(numValue);

            double numScore = 0.3;

            while (mNumVal.find()) {

                stringFound.add(mNumVal.group());
                stringFound.add(numScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }

        return stringFound;
    }

    /**
     *
     * @param dateTime
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList validDateTime(String dateTime) {

        ArrayList stringFound = new ArrayList<>();

        try {
            Pattern date = Pattern.compile("((((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])"
                    + "\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|"
                    + "((16|[2468][048]|[3579][26])00)))))+");
            Pattern time = Pattern.compile("(([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2})+");
            Matcher mDate = date.matcher(dateTime);
            Matcher mTime = time.matcher(dateTime);

            double dateTimeScore = 0.7;

            while (mDate.find()) {
                stringFound.add(mDate.group());
                stringFound.add(dateTimeScore);
            }

            while (mTime.find()) {
                stringFound.add(mTime.group());
                stringFound.add(dateTimeScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }

    /**
     * @param currencyValue
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList validCurrencyValues(String currencyValue) {

        ArrayList stringFound = new ArrayList<>();

        try {

            Pattern pCurrencyValue = Pattern.compile("((?:[1-9](?:[\\d]{0,2}(?:\\.[\\d]{3})*|[\\d]+)|0)(?:,[\\d]{0,2})?)+");
            Matcher mCV = pCurrencyValue.matcher(currencyValue);

            double cvScore = 0.4;

            while (mCV.find()) {

                stringFound.add(mCV.group());
                stringFound.add(cvScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }

    /**
     *
     * @param url
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList validURLs(String url) {

        ArrayList stringFound = new ArrayList<>();

        try {
            // Arrumar o final para que este aceite links com cadeias de p�ginas(home/projects/software/etc) .(/\\w)
            Pattern pURLs = Pattern.compile("(http[s]?://|ftp://)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)+");
            Matcher mURLs = pURLs.matcher(url);

            double urlScore = 0.5;

            while (mURLs.find()) {

                stringFound.add(mURLs.group());
                stringFound.add(urlScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }

    /**
     *
     * @param email
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList validEmail(String email) {

        ArrayList stringFound = new ArrayList<>();

        try {
            Pattern pEmail = Pattern.compile("([\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3})+");
            Matcher mEmail = pEmail.matcher(email);

            double emailScore = 0.8;

            while (mEmail.find()) {

                stringFound.add(mEmail.group());
                stringFound.add(emailScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }

    /**
     *
     * @param phoneNum
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList numPhoneValid(String phoneNum) {

        ArrayList stringFound = new ArrayList<>();

        try {
            Pattern pPhoneNum = Pattern.compile("(\\(?\\d{2}\\)?[\\s-]?\\d{4}-?\\d{4})+");
            Matcher mPNum = pPhoneNum.matcher(phoneNum);

            double pNumScore = 0.9;

            while (mPNum.find()) {

                stringFound.add(mPNum.group());
                stringFound.add(pNumScore);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }

    /**
     *
     * @param zipCodes
     * @return ArrayList contendo as pontuações e os campos candidatos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList zipCodeValid(String zipCodes) {

        ArrayList stringFound = new ArrayList<>();

        try {

            Pattern zipBR = Pattern.compile("([0-9]{2}[0-9]{3}-[0-9]{3})+");
            Pattern zipEUA = Pattern.compile("(\\d{5}(-\\d{4})?)+");

            //Match the given string with the pattern  
            Matcher mZipBR = zipBR.matcher(zipCodes);
            Matcher mZipEua = zipEUA.matcher(zipCodes);

            double zCodeScore = 0.9;

            //   boolean matchFoundBR = mZipBR.matches();
            //    boolean matchFoundEUA = mZipEua.matches();
            // if (matchFoundBR) {
            while (mZipBR.find()) {

                stringFound.add(mZipBR.group());
                stringFound.add(zCodeScore);
            }
            // } else if (matchFoundEUA) {

            while (mZipEua.find()) {
                stringFound.add(mZipEua.group());
                stringFound.add(zCodeScore);
            }
            //  }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR >>>  " + error);
        }
        return stringFound;
    }
}
