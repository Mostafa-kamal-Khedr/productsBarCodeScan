package com.ddg.project.db;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Mostafa khedr on 02,يوليو,2019
 */
public class Products {

    public static String product1="3610340017421";
    public static String product2="6281006442924";
    public static String product3="888066000079";

    //makeup
    public String OFRABlissfulHighlighter = "OFRA Blissful Highlighter";
    public String OFRACosmeticsOfraXMannyMUALipSet = "OFRA Cosmetics Ofra X Manny MUA Lip Set";
    public String OFRAEyeshadowPeach = "OFRA Eyeshadow Peach";
    public String OFRALiplinerMauve = "OFRA Lipliner Mauve";
    public String OFRALipstick101 = "OFRA Lipstick 101";
    //    __________________
    public String theBalmMaryLouManizerLuminizer = "the Balm Mary-Lou Manizer Luminizer ";
    public String theBalmgetönteTagescremeBalmShelterTintedMoisturizerMedium = "theBalm getönte Tagescreme Balm Shelter Tinted Moisturizer,Medium";
    public String theBalmManizerKit = "theBalm Manizer Kit";
    public String theBalmMeetMatteHughesSincere = "theBalm Meet Matte Hughes- Sincere";
    public String theBalmShadowBlushDownBoy = "theBalm ShadowBlush, DownBoy";


    //perfume

    public String c212CarolinaHerreraForWomenPersonalFragrances = "212 Carolina Herrera For Women Personal Fragrances";
    public String CalvinKlienEternityAirForWomen = "Calvin Klien Eternity Air For Women";
    public String CarolinaHerreraEauDeParfum = "Carolina Herrera - 'Good Girl' Eau De Parfum";
    public String HugoBossHugoWomanEdP = "Hugo Boss Hugo Woman EdP";
    public String IvoireBalmain = "Ivoire Balmain";
    public String JADOREbyChristianDiorEauDeParfumSpray = "JADORE by Christian Dior Eau De Parfum Spray 1.7 oz";
    public String ManceraRosesGreedy = "Mancera Roses Greedy";
    public String RosesDeChloe = "Roses De Chloe";
    public String TomFordBlackOrchidEaudeParfum = "Tom Ford Black Orchid Eau de Parfum 100ml";
    public String VanCleefArpels = "Van-Cleef-Arpels";

    //hair
    public String BioluxeStylingGel = "Bioluxe Styling Gel";
    public String ClearScalpHairTherapyNourishingAntiShampoo = "Clear Scalp & Hair Therapy Nourishing Anti-dandruff Shampoo";
    public String DovehairtherapyoilReplacement = "Dove hair therapy oil replacement";
    public String Elviveextraordinaryoil = "Elvive extraordinary oil";
    public String GarnierFructisStyleCurlScrunchGel = "Garnier Fructis Style Curl Scrunch Gel";
    public String GarnierNutrisseNourishingColorCreme = "Garnier Nutrisse Nourishing Color Creme";
    public String GarnierNutrisseUltraColor = "Garnier Nutrisse Ultra Color";
    public String OrofluidoMask = "Orofluido Mask (250ml)";
    public String OrofluidoBeautyElixirYourHair = "Orofluido-Beauty-Elixir-Your-Hair";
    public String PanteneDamageDetoxConditioner = "Pantene Damage Detox Conditioner";


    //skin
    public String BodyScrubHempz = "Body Scrub Hempz";
    public String Cleanandclear = "Clean and clear";
    public String Hemphardworkingfootprotector = "Hemp hard working foot protector";
    public String Hempzpomegranatebodywash = "Hempz pomegranate body wash";
    public String HimalayaHerbalsPurifyingNeemFaceWash = "Himalaya Herbals Purifying Neem Face Wash";
    public String JOHNSONSBabyOil = "JOHNSON’S Baby Oil";
    public String JOHNSONShampoo = "JOHNSON’S Shampoo";
    public String NatureRepubliNEWSoothingMoistureALOEVERA = "Nature Republic NEW Soothing& Moisture ALOE VERA";
    public String NiveaCream = "Nivea Cream";
    public String TheBodyShopMiniPapayaBodyButter = "The Body Shop Mini Papaya Body Butter";


    public List<String> getProductsBarCode() {

        List<String> barCodeList = new ArrayList<>();

        barCodeList.add("3610340017421");
        barCodeList.add("6281006442924");
        barCodeList.add("888066000079");

        barCodeList.add("693102510166");     //OFRA Blissful Highlighter
        barCodeList.add("693102820166");                 //OFRA Cosmetics Ofra X Manny MUA Lip Set
        barCodeList.add("693102933880");                 //OFRA Eyeshadow Peach
        barCodeList.add("843711216571");                 //OFRA Lipliner Mauve
        barCodeList.add("843711217134");                 //OFRA Lipstick 101
        barCodeList.add("681619700583");                 //the Balm Mary-Lou Manizer Luminizer
        barCodeList.add("885291449741");                             //theBalm getönte Tagescreme Balm Shelter Tinted Moisturizer,Medium
        barCodeList.add("681619801013");                      //theBalm Manizer Kit
        barCodeList.add("681619807237");                                 //theBalm Meet Matte Hughes- Sincere
        barCodeList.add("885647551494");                                   //theBalm ShadowBlush, DownBoy
        //perfume
        barCodeList.add("786200910683");      //212 Carolina Herrera For Women Personal Fragrances
        barCodeList.add("3614225613494");              //Calvin Klien Eternity Air For Women
        barCodeList.add("8411061823514");                     //Carolina Herrera - 'Good Girl' Eau De Parfum
        barCodeList.add("737052893839");                                  //Hugo Boss Hugo Woman EdP
        barCodeList.add("723817190138");                                   //Ivoire Balmain
        barCodeList.add("3348900689428");                                   //JADORE by Christian Dior Eau De Parfum Spray 1.7 oz
        barCodeList.add("3700578511010");                                   //Mancera Roses Greedy
        barCodeList.add("3607342624849");                                  //Roses De Chloe
        barCodeList.add("072671214284");                                  //Tom Ford Black Orchid Eau de Parfum 100ml
        barCodeList.add("3252559401846");                                  //Van-Cleef-Arpels


        //hair

        barCodeList.add("050428471852");       //Bioluxe Styling Gel
        barCodeList.add("079400194183");       //Clear Scalp & Hair Therapy Nourishing Anti-dandruff Shampoo
        barCodeList.add("6281006442924");         //Dove hair therapy oil replacement
        barCodeList.add("3610340017421");          //Elvive extraordinary oil
        barCodeList.add("787461824122");           //Garnier Fructis Style Curl Scrunch Gel
        barCodeList.add("603084494286");           //Garnier Nutrisse Nourishing Color Creme
        barCodeList.add("603084410712");           //Garnier Nutrisse Ultra Color
        barCodeList.add("8432225031408");           //Orofluido Mask (250ml)
        barCodeList.add("8432225023618");           //Orofluido-Beauty-Elixir-Your-Hair
        barCodeList.add("080878177264");           //Pantene Damage Detox Conditioner

        //skin
        barCodeList.add("3610340017421");       //Body Scrub Hempz
        barCodeList.add("3574660131888");       //Clean and clear
        barCodeList.add("502819785821");         //Hemp hard working foot protector
        barCodeList.add("676280022119");          //Hempz pomegranate body wash
        barCodeList.add("8901138511784");           //JOHNSON’S Baby Oil
        barCodeList.add("8850007060314");           //JOHNSON’S Shampoo
        barCodeList.add("3574660547603");           //Nature Republic NEW Soothing& Moisture ALOE VERA
        barCodeList.add("889068023349");           //Nivea Cream
        barCodeList.add("689002620425");           //Nivea Cream
        barCodeList.add("120527593468");           //The Body Shop Mini Papaya Body Butter

        return barCodeList;
    }
}
