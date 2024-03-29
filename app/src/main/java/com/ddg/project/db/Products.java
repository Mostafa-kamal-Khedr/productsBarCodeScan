package com.ddg.project.db;

import com.ddg.project.R;
import com.ddg.project.model.ProductsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mostafa khedr on 02,يوليو,2019
 */
public class Products {

    public static String product1 = "3610340017421";
    public static String product2 = "6281006442924";
    public static String product3 = "888066000079";

    //makeup
    public String OFRABlissfulHighlighter = "OFRA Blissful Highlighter";
    public String OFRACosmeticsOfraXMannyMUALipSet = "OFRA Cosmetics Ofra X Manny MUA Lip Set";
    public String OFRAEyeshadowPeach = "OFRA Eyeshadow Peach";
    public String OFRALiplinerMauve = "OFRA Lipliner Mauve";
    public String OFRALipstick101 = "OFRA Lipstick 101";

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


    public List<ProductsModel> getMakeupList() {

        List<ProductsModel> makeUpList = new ArrayList<>();
        makeUpList.add(new ProductsModel("OFRA Blissful Highlighter", R.drawable.makeup1, "https://www.ofracosmetics.com/products/blissful-highlighter"));
        makeUpList.add(new ProductsModel("OFRA Cosmetics Ofra X Manny MUA Lip Set", R.drawable.makeup2, "https://www.ofracosmetics.com/products/long-lasting-liquid-lip-set-ofraxmannymua"));
        makeUpList.add(new ProductsModel("OFRA Eyeshadow Peach", R.drawable.makeup3, "https://www.ofracosmetics.com/products/blush-godet-pan-refill-peach?variant=41883718534"));
        makeUpList.add(new ProductsModel("OFRA Lipliner Mauve", R.drawable.makeup4, "https://www.ofracosmetics.com/products/lipliner-mauve"));
        makeUpList.add(new ProductsModel("OFRA Lipstick 101", R.drawable.makeup5, "https://www.ofracosmetics.com/products/lipstick-101"));
        makeUpList.add(new ProductsModel("the Balm Mary-Lou Manizer Luminizer", R.drawable.makeup6, "https://thebalm.com/products/man-681619700583"));
        makeUpList.add(new ProductsModel("theBalm getönte Tagescreme Balm Shelter Tinted Moisturizer,Medium", R.drawable.makeup7, "https://thebalm.com/products/balm-shelter-tinted-moisturizer"));
        makeUpList.add(new ProductsModel("theBalm Manizer Kit", R.drawable.makeup8, "https://thebalm.com/products/the-manizer-sisters"));
        makeUpList.add(new ProductsModel("theBalm Meet Matte Hughes- Sincere", R.drawable.makeup9, "https://thebalm.com/products/meet-matte-hughes"));
        makeUpList.add(new ProductsModel("theBalm ShadowBlush, DownBoy", R.drawable.makeup10, "https://thebalm.com/products/do-681619700514"));


        return makeUpList;
    }

    public List<ProductsModel> getPerfumeList() {

        List<ProductsModel> makeUpList = new ArrayList<>();
        makeUpList.add(new ProductsModel("212 Carolina Herrera For Women Personal Fragrances", R.drawable.perfume1, " https://www.carolinaherrera.com/nl/en/fragrances/212-fragrance/212-carolina-herrera-nyc/212-nyc/"));
        makeUpList.add(new ProductsModel("Calvin Klien Eternity Air For Women", R.drawable.perfume2, "https://www.calvinklein.co.uk/eternity-air-for-women-50-ml-eau-de-parfum-6522903200blu"));
        makeUpList.add(new ProductsModel("Carolina Herrera - 'Good Girl' Eau De Parfum", R.drawable.perfume3, "https://www.fruugo.ie/good-girl-perfume-by-carolina-herrera-30ml-edp/p-31282527-64947129"));
        makeUpList.add(new ProductsModel("Hugo Boss Hugo Woman EdP", R.drawable.perfume4, "https://www.fragrantica.com/perfume/Hugo-Boss/Hugo-Woman-Eau-de-Parfum-29314.html"));
        makeUpList.add(new ProductsModel("Ivoire Balmain", R.drawable.perfume5, "https://www.fragrantica.com/perfume/Pierre-Balmain/Ivoire-15557.html"));
        makeUpList.add(new ProductsModel("JADORE by Christian Dior Eau De Parfum Spray 1.7 oz", R.drawable.perfume6, "https://www.dior.com/en_us/products/beauty-Y0715201-jadore-eau-de-parfum"));
        makeUpList.add(new ProductsModel("Mancera Roses Greedy", R.drawable.perfume7, "https://www.fragrantica.com/perfume/Mancera/Roses-Greedy-18042.html"));
        makeUpList.add(new ProductsModel("Roses De Chloe", R.drawable.perfume8, "https://www.chloe.com/ca/fragrance_cod46467853gk.html"));
        makeUpList.add(new ProductsModel("Tom Ford Black Orchid Eau de Parfum 100ml", R.drawable.perfume9, "https://www.fragrantica.com/perfume/Tom-Ford/Black-Orchid-1018.html"));
        makeUpList.add(new ProductsModel("Van-Cleef-Arpels", R.drawable.perfume10, "https://www.fragrantica.com/perfume/Van-Cleef-Arpels/Van-Cleef-1102.html"));


        return makeUpList;
    }

    public List<ProductsModel> getSkinCareList() {


        List<ProductsModel> skinCareList = new ArrayList<>();
        skinCareList.add(new ProductsModel("Body Scrub Hempz", R.drawable.skin1, " https://www.hempz.com/triple-moisture-body-scrub/nl/en/fragrances/212-fragrance/212-carolina-herrera-nyc/212-nyc/"));


        skinCareList.add(new ProductsModel("Clean and clear", R.drawable.skin2, "https://www.cleanandcleararabia.com/products/blackhead-clearing/blackhead-clearing-cleanser"));


        skinCareList.add(new ProductsModel("Hemp hard working foot protector", R.drawable.skin3, "https://www.thebodyshop.com/en-gb/body/foot-care/hemp-hard-working-foot-protector/p/p000787"));


        skinCareList.add(new ProductsModel("Hempz pomegranate body wash", R.drawable.skin4, "https://www.hempz.com/pomegranate-body-wash"));
        skinCareList.add(new ProductsModel("Himalaya Herbals Purifying Neem Face Wash", R.drawable.skin5, " http://www.himalayaherbals.com/products/skincare/Purifying-Neem-Face-Wash.htm/"));


        skinCareList.add(new ProductsModel("JOHNSON’S Baby Oil", R.drawable.skin6, " https://www.johnsonsbabyarabia.com/en/johnsons-baby-oil/212-nyc/"));


        skinCareList.add(new ProductsModel("JOHNSON’S Shampoo", R.drawable.skin7, "https://www.johnsonsbabyarabia.com/en/johnsons-baby-shampoo"));


        skinCareList.add(new ProductsModel("Nature Republic NEW Soothing& Moisture ALOE VERA", R.drawable.skin8,
                "https://www.naturerepublicusa.com/products/soothing-and-moisture-aloe-vera-92-soothing-gel"));


        skinCareList.add(new ProductsModel("Nivea Cream", R.drawable.skin9, "https://www.nivea-me.com/en-me/products/nivea-creme-421641040214.html/"));


        skinCareList.add(new ProductsModel("The Body Shop Mini Papaya Body Butter", R.drawable.skin10, "https://thebodyshop.com.ph/body/bath-body/body-butters-creams/papaya-body-butter/"));


        return skinCareList;
    }


    public List<ProductsModel> getHairList() {


        List<ProductsModel> hairList = new ArrayList<>();

        hairList.add(new ProductsModel("Bioluxe Styling Gel", R.drawable.hair1, "https://www.amazon.com/BioLuxe-Sculpting-Styling-Gel-Fl/dp/B0083A897K"));
        hairList.add(new ProductsModel("Clear Scalp & Hair Therapy Nourishing Anti-dandruff Shampoo", R.drawable.hair2, "https://www.amazon.in/Therapy-Nourishing-Anti-Dandruff-Shampoo-Complete/dp/B01NCBN9WY"));
        hairList.add(new ProductsModel("Dove hair therapy oil replacement", R.drawable.hair3, "https://www.dove.com/sa/en/hair-care/hair-oil/hair-fall-rescue-oil-replacement.html"));

        hairList.add(new ProductsModel("Elvive extraordinary oil", R.drawable.hair4, "https://ounousareviews.com/product/hair/loreal-paris-elvive-extraordinary-oil-replacement/"));
        hairList.add(new ProductsModel("Garnier Fructis Style Curl Scrunch Gel", R.drawable.hair5, "https://www.garnierusa.com/about-our-brands/fructis-style/curls/curl-scrunch-controlling-gel"));
        hairList.add(new ProductsModel("Garnier Nutrisse Nourishing Color Creme", R.drawable.hair6, "https://www.garnierusa.com/about-our-brands/hair-color/nutrisse-color-creme/nourishing-color-creme-92-light-buttery-blonde"));
        hairList.add(new ProductsModel("Garnier Nutrisse Ultra Color", R.drawable.hair7, "https://www.garnierusa.com/about-our-brands/hair-color/nutrisse-ultra-color/ultra-color-br1-deepest-intense-burgundy"));
        hairList.add(new ProductsModel("Orofluido Mask (250ml)", R.drawable.hair8, "https://www.amazon.com/Orofluido-Beauty-Mask-8-45-250/dp/B004QBRX5Y?th=1"));
        hairList.add(new ProductsModel("Orofluido-Beauty-Elixir-Your-Hair", R.drawable.hair9, "https://www.amazon.com/Orofluido-Beauty-Elixir-Your-Hair/dp/B00CVT6ZU2"));
        hairList.add(new ProductsModel("Pantene Damage Detox Conditioner", R.drawable.hair10, "https://www.amazon.com/Pantene-Damage-Revitalizing-Shampoo-12-600-Fluid/dp/B00FKSWNRK"));

        return hairList;
    }

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
