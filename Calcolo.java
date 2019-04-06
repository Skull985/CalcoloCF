public static String calcoloCF(String nome, String cognome, String sesso, String dataNascita, String codCatastale){
        String response="";
        nome = nome.toUpperCase();
        cognome = cognome.toUpperCase();
        response = estrazione(cognome, false).concat(estrazione(nome, true))
                .concat(patNacita(sesso,dataNascita)).concat(codCatastale).concat(carattereControllo(codFInc));
        return response;

    }
    public static String estrazione(String valore, boolean control){
        String response="";
        if (control && valore.length()>5){
            response = valore.replaceAll("[\\^AEIOU '$]","")
                    .concat (valore.replaceAll("[^AEIOU$]","")).concat("X");;
            response = response.substring(0,1).concat(response.substring(2));

        }else{
            response = valore.replaceAll("[\\^AEIOU '$]","")
                    .concat (valore.replaceAll("[^AEIOU$]",""))
                    .concat("X");
        }

        return response.substring(0,3);
    }
    public static String carattereControllo (String codiceF){
        HashMap<Integer, Character> controlC = new HashMap<Integer, Character>();
        HashMap<Character, Integer> pari = new HashMap<Character, Integer>();
        HashMap<Character, Integer> dispari = new HashMap<Character, Integer>();
        int somma =0;

        for(int i=65;i<91;i++){
            controlC.put((i-65),(char)i);
            pari.put((char)i,(i-65));

        }

        for (int i=48; i<58;i++){
            pari.put((char)i,(i-48));

        }
        dispari.put('0',1);
        dispari.put('1',0);
        dispari.put('2',5);
        dispari.put('3',7);
        dispari.put('4',9);
        dispari.put('5',13);
        dispari.put('6',15);
        dispari.put('7',17);
        dispari.put('8',19);
        dispari.put('9',21);
        dispari.put('A',1);
        dispari.put('B',0);
        dispari.put('C',5);
        dispari.put('D',7);
        dispari.put('E',9);
        dispari.put('F',13);
        dispari.put('G',15);
        dispari.put('H',17);
        dispari.put('I',19);
        dispari.put('J',21);
        dispari.put('K',2);
        dispari.put('L',4);
        dispari.put('M',18);
        dispari.put('N',20);
        dispari.put('O',11);
        dispari.put('P',3);
        dispari.put('Q',6);
        dispari.put('R',8);
        dispari.put('S',12);
        dispari.put('T',14);
        dispari.put('U',16);
        dispari.put('V',10);
        dispari.put('W',22);
        dispari.put('X',25);
        dispari.put('Y',24);
        dispari.put('Z',23);

        for (int j=0;j<codiceF.length();j++){

            if(j%2==0){
                somma+=dispari.get(codiceF.charAt(j));
            }else{
                somma+=pari.get(codiceF.charAt(j));
            }
        }
        somma = somma%26;

        return Character.toString(controlC.get(somma));
    }

    public static String patNacita(String sesso,String dataNascita){
        int giorno=0;
        int k=0;
        String mese ="";
        String response="";
        HashMap<Integer, Character> idMese = new HashMap<Integer, Character>();
        for(int i=65;i<85;i++){
            k++;
            if (i== 70 || i== 71 || i== 73 || i== 74 || i== 75 || i== 78 || i== 79 || i== 81){
                k--;
                continue;
            }
            else{
                idMese.put(k,(char)i);
            }

        }
        if (sesso.equalsIgnoreCase("F")){
            giorno = Integer.parseInt(dataNascita.substring(0,2)) + 40;
        }else {
            giorno = Integer.parseInt(dataNascita.substring(0, 2));
        }
        mese = idMese.get(Integer.parseInt(dataNascita.substring(3,5))).toString();
        response = dataNascita.substring(8).concat(mese).concat(String.format("%02d",giorno));
        return response;
    }
