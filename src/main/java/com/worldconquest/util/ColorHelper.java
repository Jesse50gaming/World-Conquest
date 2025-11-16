package com.worldconquest.util;
import java.util.HashMap;
import java.util.Random;

import com.jme3.math.ColorRGBA;

public class ColorHelper {
    private HashMap<String, ColorRGBA> countryColorMap = new HashMap<>(); 

    public ColorRGBA[] COLORS = new ColorRGBA[] {
        ColorRGBA.Red,
        ColorRGBA.Green, 
        ColorRGBA.Blue, 
        ColorRGBA.Yellow, 
        ColorRGBA.Magenta,
        ColorRGBA.Cyan,       
        ColorRGBA.Orange, 
        ColorRGBA.Pink, 
        ColorRGBA.Brown, 
        ColorRGBA.DarkGray
    };

    private enum ColorNum {
        Red(0), Green(1), Blue(2), Yellow(3), Magenta(4), Cyan(5), Orange(6), Pink(7), Brown(8), DarkGray(9);

        private final int num;

        ColorNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
        
    }

    public ColorHelper() {
        setCountryColors();
    }

    public ColorRGBA randomColor() {
        Random random = new Random();
        return COLORS[random.nextInt(10)];
    }

    public ColorRGBA getCountryColor(String name) {
        if (countryColorMap.get(name) != null) {
            return countryColorMap.get(name);
        }
        return ColorRGBA.Black;
        
    }

    private void setCountryColors() {
        
        countryColorMap.put("Papua New Guinea", COLORS[0]);
        countryColorMap.put("Cambodia", COLORS[1]);
        countryColorMap.put("Kazakhstan", COLORS[2]);
        countryColorMap.put("Paraguay", COLORS[3]);
        countryColorMap.put("Bahamas", COLORS[4]);
        countryColorMap.put("Solomon Islands", COLORS[5]);
        countryColorMap.put("Montserrat", COLORS[6]);
        countryColorMap.put("Mali", COLORS[7]);
        countryColorMap.put("Marshall Islands", COLORS[8]);
        countryColorMap.put("Panama", COLORS[9]);
        countryColorMap.put("Guadeloupe", COLORS[0]);
        countryColorMap.put("Tanzania, United Republic of", COLORS[1]);
        countryColorMap.put("Argentina", COLORS[2]);
        countryColorMap.put("Macau, China", COLORS[3]);
        countryColorMap.put("Seychelles", COLORS[4]);
        countryColorMap.put("Belize", COLORS[5]);
        countryColorMap.put("Zambia", COLORS[6]);
        countryColorMap.put("Bahrain", COLORS[7]);
        countryColorMap.put("Congo", COLORS[8]);
        countryColorMap.put("Guinea-Bissau", COLORS[9]);
        countryColorMap.put("Namibia", COLORS[0]);
        countryColorMap.put("Finland", COLORS[1]);
        countryColorMap.put("Comoros", COLORS[2]);
        countryColorMap.put("Georgia", COLORS[3]);
        countryColorMap.put("Saint Kitts and Nevis", COLORS[4]);
        countryColorMap.put("Yemen", COLORS[5]);
        countryColorMap.put("Puerto Rico", COLORS[6]);
        countryColorMap.put("Eritrea", COLORS[7]);
        countryColorMap.put("Viet Nam", COLORS[8]);
        countryColorMap.put("Madagascar", COLORS[9]);
        countryColorMap.put("Aruba", COLORS[0]);
        countryColorMap.put("PPL", COLORS[1]);
        countryColorMap.put("Sweden", COLORS[2]);
        countryColorMap.put("Malawi", COLORS[3]);
        countryColorMap.put("Andorra", COLORS[4]);
        countryColorMap.put("Sudan, The Republic of", COLORS[5]);
        countryColorMap.put("Liechtenstein", COLORS[6]);
        countryColorMap.put("Poland", COLORS[7]);
        countryColorMap.put("Bulgaria", COLORS[8]);
        countryColorMap.put("Jordan", COLORS[9]);
        countryColorMap.put("Tunisia", COLORS[0]);
        countryColorMap.put("Wallis and Futuna Islands", COLORS[1]);
        countryColorMap.put("Côte d'Ivoire", COLORS[2]);
        countryColorMap.put("United Arab Emirates", COLORS[3]);
        countryColorMap.put("Tuvalu", COLORS[4]);
        countryColorMap.put("Kenya", COLORS[5]);
        countryColorMap.put("French Polynesia", COLORS[6]);
        countryColorMap.put("Lebanon", COLORS[7]);
        countryColorMap.put("Djibouti", COLORS[8]);
        countryColorMap.put("Azerbaijan", COLORS[9]);
        countryColorMap.put("Cuba", COLORS[0]);
        countryColorMap.put("Czech Republic", COLORS[1]);
        countryColorMap.put("Mauritania", COLORS[2]);
        countryColorMap.put("Saint Lucia", COLORS[3]);
        countryColorMap.put("Guernsey", COLORS[4]);
        countryColorMap.put("Israel", COLORS[5]);
        countryColorMap.put("San Marino", COLORS[6]);
        countryColorMap.put("Australia", COLORS[7]);
        countryColorMap.put("Tajikistan", COLORS[8]);
        countryColorMap.put("Myanmar", COLORS[9]);
        countryColorMap.put("Cameroon", COLORS[0]);
        countryColorMap.put("Gibraltar", COLORS[1]);
        countryColorMap.put("Cyprus", COLORS[2]);
        countryColorMap.put("Northern Mariana Islands", COLORS[3]);
        countryColorMap.put("Malaysia", COLORS[4]);
        countryColorMap.put("Oman", COLORS[5]);
        countryColorMap.put("Iceland", COLORS[6]);
        countryColorMap.put("Armenia", COLORS[7]);
        countryColorMap.put("Gabon", COLORS[8]);
        countryColorMap.put("Luxembourg", COLORS[9]);
        countryColorMap.put("Brazil", COLORS[ColorNum.Green.getNum()]);
        countryColorMap.put("Korea, Republic of", COLORS[1]);
        countryColorMap.put("Turks and Caicos Islands", COLORS[2]);
        countryColorMap.put("Algeria", COLORS[3]);
        countryColorMap.put("Slovenia", COLORS[4]);
        countryColorMap.put("Jersey", COLORS[5]);
        countryColorMap.put("Antigua and Barbuda", COLORS[6]);
        countryColorMap.put("Colombia", COLORS[7]);
        countryColorMap.put("Ecuador", COLORS[8]);
        countryColorMap.put("Taiwan, China", COLORS[9]);
        countryColorMap.put("Vanuatu", COLORS[0]);
        countryColorMap.put("Venezuela, Bolivarian Rep. of", COLORS[1]);
        countryColorMap.put("Italy", COLORS[2]);
        countryColorMap.put("Honduras", COLORS[3]);
        countryColorMap.put("Nauru", COLORS[4]);
        countryColorMap.put("Haiti", COLORS[5]);
        countryColorMap.put("Russian Federation", COLORS[6]);
        countryColorMap.put("Afghanistan", COLORS[7]);
        countryColorMap.put("Burundi", COLORS[8]);
        countryColorMap.put("Singapore", COLORS[9]);
        countryColorMap.put("French Guiana", COLORS[0]);
        countryColorMap.put("American Samoa", COLORS[1]);
        countryColorMap.put("West Bank and Gaza Strip", COLORS[2]);
        countryColorMap.put("Netherlands", COLORS[3]);
        countryColorMap.put("China", COLORS[ColorNum.Red.getNum()]);
        countryColorMap.put("Martinique", COLORS[5]);
        countryColorMap.put("Saint Pierre and Miquelon", COLORS[6]);
        countryColorMap.put("Kyrgyzstan", COLORS[7]);
        countryColorMap.put("Virgin Islands (US)", COLORS[8]);
        countryColorMap.put("Bhutan", COLORS[9]);
        countryColorMap.put("Romania", COLORS[0]);
        countryColorMap.put("Togo", COLORS[1]);
        countryColorMap.put("Falkland Islands (Malvinas)", COLORS[2]);
        countryColorMap.put("Philippines", COLORS[3]);
        countryColorMap.put("Uzbekistan", COLORS[4]);
        countryColorMap.put("British Virgin Islands", COLORS[5]);
        countryColorMap.put("Zimbabwe", COLORS[6]);
        countryColorMap.put("Montenegro", COLORS[7]);
        countryColorMap.put("Indonesia", COLORS[8]);
        countryColorMap.put("Dominica", COLORS[9]);
        countryColorMap.put("Benin", COLORS[0]);
        countryColorMap.put("Angola", COLORS[1]);
        countryColorMap.put("South Sudan, The Republic of", COLORS[2]);
        countryColorMap.put("Portugal", COLORS[3]);
        countryColorMap.put("Brunei Darussalam", COLORS[4]);
        countryColorMap.put("New Caledonia", COLORS[5]);
        countryColorMap.put("Grenada", COLORS[6]);
        countryColorMap.put("Greece", COLORS[7]);
        countryColorMap.put("Cayman Islands", COLORS[8]);
        countryColorMap.put("Mongolia", COLORS[9]);
        countryColorMap.put("Latvia", COLORS[0]);
        countryColorMap.put("Morocco", COLORS[1]);
        countryColorMap.put("Guatemala", COLORS[2]);
        countryColorMap.put("Guyana", COLORS[3]);
        countryColorMap.put("Iraq", COLORS[4]);
        countryColorMap.put("Chile", COLORS[5]);
        countryColorMap.put("Nepal", COLORS[6]);
        countryColorMap.put("Korea, Dem. People's Rep. of", COLORS[7]);
        countryColorMap.put("Isle of Man", COLORS[8]);
        countryColorMap.put("Ukraine", COLORS[9]);
        countryColorMap.put("Ghana", COLORS[0]);
        countryColorMap.put("Anguilla", COLORS[1]);
        countryColorMap.put("India", COLORS[2]);
        countryColorMap.put("Hong Kong, China", COLORS[3]);
        countryColorMap.put("Canada", COLORS[ColorNum.Red.getNum()]);
        countryColorMap.put("Maldives", COLORS[5]);
        countryColorMap.put("Turkey", COLORS[6]);
        countryColorMap.put("Belgium", COLORS[7]);
        countryColorMap.put("South Africa", COLORS[8]);
        countryColorMap.put("Trinidad and Tobago", COLORS[9]);
        countryColorMap.put("Lao People's Dem. Rep.", COLORS[0]);
        countryColorMap.put("Bermuda", COLORS[1]);
        countryColorMap.put("Central African Republic", COLORS[2]);
        countryColorMap.put("Jamaica", COLORS[3]);
        countryColorMap.put("Peru", COLORS[4]);
        countryColorMap.put("Turkmenistan", COLORS[5]);
        countryColorMap.put("Germany", COLORS[6]);
        countryColorMap.put("Fiji", COLORS[7]);
        countryColorMap.put("Tokelau", COLORS[8]);
        countryColorMap.put("United States", COLORS[ColorNum.Blue.getNum()]);
        countryColorMap.put("Guinea", COLORS[0]);
        countryColorMap.put("Somalia", COLORS[1]);
        countryColorMap.put("Chad", COLORS[2]);
        countryColorMap.put("Sao Tome and Principe", COLORS[3]);
        countryColorMap.put("Thailand", COLORS[4]);
        countryColorMap.put("Equatorial Guinea", COLORS[5]);
        countryColorMap.put("Kiribati", COLORS[6]);
        countryColorMap.put("Costa Rica", COLORS[7]);
        countryColorMap.put("Congo, Democratic Republic of the", COLORS[8]);
        countryColorMap.put("Nigeria", COLORS[9]);
        countryColorMap.put("Kuwait", COLORS[0]);
        countryColorMap.put("Croatia", COLORS[1]);
        countryColorMap.put("Syrian Arab Republic", COLORS[2]);
        countryColorMap.put("Macedonia, The former Yugoslav Rep. of", COLORS[3]);
        countryColorMap.put("Uruguay", COLORS[4]);
        countryColorMap.put("Sri Lanka", COLORS[5]);
        countryColorMap.put("Faeroe Islands", COLORS[6]);
        countryColorMap.put("Cook Islands", COLORS[7]);
        countryColorMap.put("Timor-Leste", COLORS[8]);
        countryColorMap.put("United Kingdom", COLORS[ColorNum.Red.getNum()]);
        countryColorMap.put("Switzerland", COLORS[0]);
        countryColorMap.put("Samoa", COLORS[1]);
        countryColorMap.put("Spain", COLORS[2]);
        countryColorMap.put("Liberia", COLORS[3]);
        countryColorMap.put("Burkina Faso", COLORS[4]);
        countryColorMap.put("Swaziland", COLORS[5]);
        countryColorMap.put("Palau", COLORS[6]);
        countryColorMap.put("Estonia", COLORS[7]);
        countryColorMap.put("Libyan Arab Jamahiriya", COLORS[8]);
        countryColorMap.put("Niue", COLORS[9]);
        countryColorMap.put("Austria", COLORS[0]);
        countryColorMap.put("Mozambique", COLORS[1]);
        countryColorMap.put("El Salvador", COLORS[2]);
        countryColorMap.put("Monaco", COLORS[3]);
        countryColorMap.put("Guam", COLORS[4]);
        countryColorMap.put("Lesotho", COLORS[5]);
        countryColorMap.put("Tonga", COLORS[6]);
        countryColorMap.put("Western Sahara", COLORS[7]);
        countryColorMap.put("Hungary", COLORS[8]);
        countryColorMap.put("Réunion", COLORS[9]);
        countryColorMap.put("Japan", COLORS[0]);
        countryColorMap.put("Belarus", COLORS[1]);
        countryColorMap.put("Mauritius", COLORS[2]);
        countryColorMap.put("Albania", COLORS[3]);
        countryColorMap.put("Norfolk Island", COLORS[4]);
        countryColorMap.put("New Zealand", COLORS[5]);
        countryColorMap.put("Senegal", COLORS[6]);
        countryColorMap.put("Moldova, Republic of", COLORS[7]);
        countryColorMap.put("Iran, Islamic Rep. of", COLORS[8]);
        countryColorMap.put("Ethiopia", COLORS[9]);
        countryColorMap.put("Egypt", COLORS[0]);
        countryColorMap.put("Sierra Leone", COLORS[1]);
        countryColorMap.put("Bolivia", COLORS[2]);
        countryColorMap.put("Malta", COLORS[3]);
        countryColorMap.put("Saudi Arabia", COLORS[4]);
        countryColorMap.put("Cape Verde", COLORS[5]);
        countryColorMap.put("Pakistan", COLORS[6]);
        countryColorMap.put("Gambia", COLORS[7]);
        countryColorMap.put("Ireland", COLORS[8]);
        countryColorMap.put("Qatar", COLORS[9]);
        countryColorMap.put("Slovakia", COLORS[0]);
        countryColorMap.put("Serbia", COLORS[1]);
        countryColorMap.put("France", COLORS[2]);
        countryColorMap.put("Lithuania", COLORS[3]);
        countryColorMap.put("Bosnia and Herzegovina", COLORS[4]);
        countryColorMap.put("Niger", COLORS[5]);
        countryColorMap.put("Rwanda", COLORS[6]);
        countryColorMap.put("Bangladesh", COLORS[7]);
        countryColorMap.put("Barbados", COLORS[8]);
        countryColorMap.put("Nicaragua", COLORS[9]);
        countryColorMap.put("Norway", COLORS[0]);
        countryColorMap.put("Botswana", COLORS[1]);
        countryColorMap.put("Saint Vincent and the Grenadines", COLORS[2]);
        countryColorMap.put("Denmark", COLORS[3]);
        countryColorMap.put("Dominican Republic", COLORS[4]);
        countryColorMap.put("Mexico", COLORS[ColorNum.Green.getNum()]);
        countryColorMap.put("Uganda", COLORS[6]);
        countryColorMap.put("Suriname", COLORS[7]);
        countryColorMap.put("Saint Helena", COLORS[8]);
        countryColorMap.put("Greenland", COLORS[9]);

    }

}
