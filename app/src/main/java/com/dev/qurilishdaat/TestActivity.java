package com.dev.qurilishdaat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {
    private List<TestModel> testList;
    private List<Integer> userAnswers = new ArrayList<>();
    private int currentIndex = 0;

    private TextView questionText, timerText;
    private RadioGroup optionsGroup;
    private Button nextButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30 * 60 * 1000; // 30 daqiqa

    // Foydalanuvchi ma’lumotlari
    private String name, surname, group,variant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        group = intent.getStringExtra("group");
        variant = intent.getStringExtra("variant");

        questionText = findViewById(R.id.question_text);
        timerText = findViewById(R.id.timer_text);
        optionsGroup = findViewById(R.id.options_group);
        nextButton = findViewById(R.id.next_button);

        testList = loadTestQuestions();

        startTimer();
        showQuestion();

        nextButton.setOnClickListener(v -> {
            if (optionsGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Iltimos, javobni belgilang", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedIndex = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));
            userAnswers.add(selectedIndex);

            currentIndex++;
            if (currentIndex < testList.size()) {
                showQuestion();
            } else {
                showResult();
            }
        });
    }
    private void showQuestion() {
        TestModel test = testList.get(currentIndex);
        questionText.setText("Savol " + (currentIndex + 1) + ": " + test.getQuestion());

        optionsGroup.removeAllViews();
        for (String option : test.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            optionsGroup.addView(radioButton);
        }
        optionsGroup.clearCheck();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerUI();
            }

            @Override



            public void onFinish() {
                Toast.makeText(TestActivity.this, "Vaqt tugadi!", Toast.LENGTH_SHORT).show();
                showResult();
            }
        }.start();
    }

    private void updateTimerUI() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerText.setText("Vaqt: " + timeFormatted);
    }

    private void showResult() {
        countDownTimer.cancel();
        Intent intent = new Intent(this, Natija.class);
        intent.putExtra("userAnswers", new ArrayList<>(userAnswers));
        intent.putExtra("testList", new Gson().toJson(testList));

        // Foydalanuvchi ma’lumotlarini ham uzatish
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        intent.putExtra("group", group);
        intent.putExtra("variant", variant);

        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Diqqat!")
                .setMessage("Test tugamaguncha chiqish mumkin emas.")
                .setPositiveButton("OK", null)
                .show();
    }
    private List<TestModel> loadTestQuestions() {
        List<TestModel> list = new ArrayList<>();
        if ("Variant 1".equals(variant)) {
            list.add(new TestModel(
                    "Qaysi qurilma kompyuterning qo’shimcha qurilmasi hisoblanadi?",
                    Arrays.asList("Printer", "Videoproyektor", "Windows", "Videokarta"),
                    1
            ));
            list.add(new TestModel(
                    "Funksional imkoniyatlari bo’yicha zamonaviy kompyuterlar qanday guruhlarga bo’linadi?",
                    Arrays.asList("Katta, kichik, server, shaxsiy, ko’chma",
                            "Super, katta, kichik, shaxsiy, ko’chma",
                            "Super, katta, kichik, server, shaxsiy, ko’chma",
                            "Katta, kichik, server, shaxsiy, ko’chma, mikro"),
                    2
            ));

            list.add(new TestModel(
                    "Quyidagilarning qaysi biri kichik kompyuter hisoblanadi?",
                    Arrays.asList("IBM 4300", "HP 9000", "Intel ASCI Red", "PentiumIV Server"),
                    1
            ));

            list.add(new TestModel(
                    "Quyidagilarning qaysi biri ko’chma kompyuter hisoblanadi?",
                    Arrays.asList("Intel ASCI Red", "Lap Top", "HP 9000", "IBM 4300"),
                    1
            ));

            list.add(new TestModel(
                    "Zamonaviy mikroprosessorlarni ishlab chiqishga ixtisoslashgan kompaniyaning nomini ko’rsating?",
                    Arrays.asList("Epson", "IBM", "Intel", "Microsoft"),
                    2
            ));
            list.add(new TestModel(
                    "Qaysi javobda kompyuterning xotiralash qurilmalari keltirilgan?",
                    Arrays.asList("Windows, OS/2, Unix", "DIMM, SIMM, RIMM", "AMD, Cyrix, Dell", "Word, Excel, Access"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi qurilma kompyuterga ma’lumotlarni faqat kiritishga xizmat qiladi?",
                    Arrays.asList("Klaviatura", "Sichqoncha", "Monitor", "Modem"),
                    0
            ));

            list.add(new TestModel(
                    "Qaysi qurilma kompyuterga ma’lumotlarni faqat kiritishga xizmat qiladi?",
                    Arrays.asList("Sichqoncha", "Klaviatura", "Monitor", "Modem"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi xotira prosessor bilan to’g’ridan-to’g’ri ishlaydi?",
                    Arrays.asList("DVD", "Qattiq disk", "Tezkor xotira", "CD-ROM"),
                    2
            ));

            list.add(new TestModel(
                    "Prosessorning ishlash tezligi asosan qaysi parametri bilan o’lchanadi?",
                    Arrays.asList("O’lchamlari bilan", "Talab qiladigan elektr energiyasi miqdori bilan", "Takt chastotasi bilan", "Xotirasining sig’imi bilan"),
                    2
            ));
            list.add(new TestModel(
                    "Kompyuterning qaysi qurilmasi tasvirlarni kodlashga xizmat qiladi?",
                    Arrays.asList("Monitor", "Videokarta", "Videoport", "Qattiq disk"),
                    1
            ));

            list.add(new TestModel(
                    "Ishlaydigan ma’lumotlarining turlari bo’yicha zamonaviy kompyuterlar qanday turkumlarga bo’linadi?",
                    Arrays.asList("Alfavit-raqamli va analogli", "Gibrid", "Alfavit-raqamli, analogli, gibrid", "Analogli"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi qurilma kompyuterning asosiy qurilmasi hisoblanadi?",
                    Arrays.asList("Videoproyektor", "Videokarta", "Printer", "Windows"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi javobda kompyuterning asosiy qurilmalari keltirilgan?",
                    Arrays.asList("Tezkor xotira, BIOS, videokarta", "Digitayzer, plotter, modem", "Prosessor, plotter, printer", "Audiokolonka, himoya filtrlari, uzluksiz ta’minlash bloki"),
                    0
            ));

            list.add(new TestModel(
                    "Qaysi javobda kompyuterning qurilmalari keltirilmagan?",
                    Arrays.asList("Audiokolonka, himoya filtrlari, uzluksiz ta’minlash bloki", "AMD, Cyrix, Dell", "Digitayzer, plotter, modem", "SIMM, DIMM, RIMM"),
                    1
            ));
            list.add(new TestModel(
                    "Portlar nima?",
                    Arrays.asList("Tezkor xotira", "Qurilmalarni kompyuter bilan ulash vositasi", "Energiya manbai", "Monitor ekrani"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi qurilma ma’lumotlarni faqat chiqarishga xizmat qiladi?",
                    Arrays.asList("Modem", "Plotter", "Klaviatura", "Skaner"),
                    1
            ));

            list.add(new TestModel(
                    "Texnologiya so‘zining ma’nosi nima?",
                    Arrays.asList("ko‘nikma", "san’at, mahorat, ko‘nikma", "san’at mahorat", "mahorat"),
                    1
            ));

            list.add(new TestModel(
                    "O’zbekistonda web-saytlariga ega maktablarning ulushi (%)da",
                    Arrays.asList("0.8%", "1.0%", "0.6%", "1.2%"),
                    0
            ));

            list.add(new TestModel(
                    "Zamonaviy kompyuter tizimi qanday qurilmalar guruhidan tashkil topgan?",
                    Arrays.asList("Tashqi qurilmalardan", "Asosiy va qo’shimcha qurilmalardan", "Asosiy, tashqi va qo’shimcha qurilmalardan", "Asosiy va tashqi qurilmalardan"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi javobda kompyuterning qo’shimcha qurilmalari keltirilgan?",
                    Arrays.asList("Prosessor, plotter, printer", "Audiokolonka, himoya filtrlari, uzluksiz ta’minlash bloki", "Digitayzer, plotter, modem", "Texkor xotira, BIOS, videokarta"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi xotiralash qurilmasi diskli emas?",
                    Arrays.asList("Strimer", "CD-ROM", "DVD", "Qattiq disk"),
                    0
            ));

            list.add(new TestModel(
                    "Тarjimon dasturi?",
                    Arrays.asList("WinDJView", "Prompt", "Adobe Acrobat Reader", "WinZip"),
                    1
            ));

            list.add(new TestModel(
                    "Mikroprosessorning vazifasi nimadan iborat?",
                    Arrays.asList("Chop etuvchi qurilmasi", "Xotira qurilmasi", "Kompyuterning tashqi qurilmasi", "Ma’lumotlarni berilgan programma asosida qayta ishlaydigan qurilmasi"),
                    3
            ));

            list.add(new TestModel(
                    "Kompyuterning ko‘p sonli turli xil mikrosxemalari joylashadigan qismi qaysi javobda to‘g‘ri ko‘rsatilgan?",
                    Arrays.asList("Monitor", "Tizimli plata", "Sistema bloki", "Mikroprosessor"),
                    1
            ));
        }
        else if ("Variant 2".equals(variant)) {
            list.add(new TestModel(
                    "Kompyuterli texnologiyaning konseptual asoslari nimalardan iborat?",
                    Arrays.asList(
                            "O’qitish, moslashuvchanlik tamoyili, o’qitishning muloqotli xarakterdaligi, boshqaruvchanlik, o’qitishning chegaralanmaganligi.",
                            "O’qitish, moslashuvchanlik tamoyili, o’qitishning muloqotli xarakterdaligi, boshqaruvchanlik, o’quvchining kompyuter bilan aloqasi barcha ko’rinishda amalga oshirilishi mumkin, o’qitishning chegaralanmaganligi.",
                            "O’qitish, moslashuvchanlik tamoyili, o’quvchining kompyuter bilan aloqasi barcha ko’rinishda amalga oshirilishi mumkin, yakka va guruhli ishning optimal mosligi, o’qitishning chegaralanmaganligi.",
                            "O’qitish, moslashuvchanlik tamoyili, o’qitishning muloqotli xarakterdaligi, boshqaruvchanlik, o’quvchining kompyuter bilan aloqasi barcha ko’rinishda amalga oshirilishi mumkin, yakka va guruhli ishning optimal mosligi, o’qitishning chegaralanmaganligi."
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Kompyuter turlarini ko‘rsating?",
                    Arrays.asList(
                            "Katta, kichik, davolovchi, film yaratuvchi",
                            "Raqamili, analogli (uzluksiz), raqamli-analogli, maxsuslashtrilgan",
                            "Rangli, ovozli, kuylovchi, bastalovchi, maxsuslashtirilgan",
                            "Analogli (uzluksiz), gapiruvchi, tikuvchi"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Yangi axbarot texnalogiyasi tarkibiga kiruvchi vositalarini anig’lang?",
                    Arrays.asList(
                            "1,2,3,6,10",
                            "Hammasi",
                            "4,5,7,8,9",
                            "1,2,3"
                    ),
                    3
            ));

            list.add(new TestModel(
                    "Masalaning quyilishidan nimalar aniqlanadi?",
                    Arrays.asList(
                            "Dasturning bajarilish vakti",
                            "Nima berilgan va nimani topish kerakligi",
                            "Algoritmning uzunligi",
                            "Zaruriy xotira xajmi"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Axbarot tizimining maqsadi qaysi bandda to’g’ri keltirilgan?",
                    Arrays.asList(
                            "Yuqoridagi barcha javoblarning to’g’risi yo’q",
                            "Zarur bo’lgan yangi axbarotni ishlab chiqarishdan iborat",
                            "Insonning biror bir ishni keyinga qoldirish",
                            "Insonning biror – bir ishini bajarishdan iborat"
                    ),
                    3
            ));
            list.add(new TestModel(
                    "Quyidagi jumlalardan qaysi biri masalaning matematik modelini tuzish jarayonini to‘g‘ri ifodalaydi?",
                    Arrays.asList(
                            "Masalani blok-sxemalarda ifodalash",
                            "Algoritmni to‘g‘ri tanlash",
                            "Masalani matematika tilida tavsiflash",
                            "Masala yechimini topish"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Avtomatlashtirilgan loyihalash vositalari yordamida qanday loyihalarni yaratish mumkin?",
                    Arrays.asList(
                            "Turli mexanizmlarning loyihalarini",
                            "Turli bezaklarni",
                            "Barchasini",
                            "Binolarning loyihalarini"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "O‘quv jarayonini tashkil qilish quyi tizimi (elektron dekanat) … hisoblanadi.",
                    Arrays.asList(
                            "namunaviy generatsiyalanadigan modullarni yaratish, foydalanuvchilarni ro‘yxatga olish va ularga belgilangan huquqlarni berish, barcha asosiy modullarning o‘zaro ishlashi hisoblanadi.",
                            "axborot resurslarini foydalanuvchilarning vakolatiga hamda o‘quv yurti tomonidan belgilanadigan shartlarga muvofiq to‘plash, saqlash va taqdim qilish hisoblanadi.",
                            "o‘quv guruhlar, mashg‘ulotlar jadvali, o‘quv jarayonining borishi yuzasidan nazoratni va boshqarishni shakllantirish.",
                            "barcha toifadagi foydalanuvchilarning shaxsiy yig‘ma jildini yaratish va olib borish hisoblanadi."
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Elektron dekanat funksiyalari …",
                    Arrays.asList(
                            "o‘quvchi va o‘qituvchilarning o‘quv faoliyatini tashkil qilish.",
                            "tegishli o‘quv guruhlarini shakllantirish hisoblanadi, bu o‘quvchi va o‘qituvchilarning o‘quv faoliyatini tashkil qilish, virtual vakillik aniq ishini ta’minlash imkonini beradi.",
                            "o‘quv-metodik ta’minotdan on-line rejimida foydalanish uchun o‘quv-metodik ta’minotning o‘quv jarayonida matnli materiallar, audio va videomateriallar, modellashtiruvchi tizimlar, avtomatlashtirilgan laboratoriya praktikumlar, bir nechta tipdagi matnli topshiriqlarni to‘plash va amalga oshirish hisoblanadi.",
                            "o‘quv-metodik ta’minotdan on-line rejimida foydalanish uchun o‘quv-metodik ta’minotning o‘quv jarayonida matnli materiallar, audio va videomateriallar, gepermatnli qo‘llanmalar, grafik tasvirlar, o‘quv kompyuter dasturlari, bir nechta tipdagi matnli topshiriqlarni to‘plash va amalga oshirish hisoblanadi."
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Hisobga olish quyi tizim (elektron xodimlar bo‘limi) … hisoblanadi.",
                    Arrays.asList(
                            "barcha toifadagi foydalanuvchilarning shaxsiy yig‘ma jildini yaratish va olib borish hisoblanadi.",
                            "namunaviy generatsiyalanadigan modullarni yaratish, foydalanuvchilarni ro‘yxatga olish va ularga belgilangan huquqlarni berish, barcha asosiy modullarning o‘zaro ishlashi hisoblanadi.",
                            "o‘quv guruhlar, mashg‘ulotlar jadvali, o‘quv jarayonining borishi yuzasidan nazoratni va boshqarishni shakllantirish.",
                            "axborot resurslarini foydalanuvchilarning vakolatiga hamda o‘quv yurti tomonidan belgilanadigan shartlarga muvofiq to‘plash, saqlash va taqdim qilish hisoblanadi."
                    ),
                    0
            ));
            list.add(new TestModel(
                    "Kompyuter viruslari nima?",
                    Arrays.asList(
                            "Kompyuterning dasturiy ta’minotini ishdan chiqaruvchi dasturiy vositalar",
                            "Biologik viruslarning bir turi",
                            "Qurilmalarni ishdan chiqaruvchi nuqsonlar",
                            "Zamonaviy formatlash vositasi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Antivirus dasturlar joylashgan qatorni ko’rsating.",
                    Arrays.asList(
                            "DrWeb, Kasperskiy, AVP",
                            "Kasperskiy, WinRar, DrWeb",
                            "WinRar, DrWeb, AVP",
                            "Access, WinRar, AVP"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Kompyuterlarning dasturiy ta’minoti qanday dasturlar majmuidan tashkil topadi?",
                    Arrays.asList(
                            "Tizimli va amaliy dasturlardan, utilitlardan hamda translyatorlardan",
                            "Utilitlardan va translyatorlardan",
                            "Tizimli dasturlar va translyatorlardan",
                            "Tizimli dasturlar va utilitlardan"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Formatlash vositalarining vazifalari qanday?",
                    Arrays.asList(
                            "Magnit disklari sathini sektor va klasterlarga bo’ladi",
                            "Kompyuter viruslarini zararsizlantiradi",
                            "Ekrandagi matnlarni turli formatlarga soladi",
                            "Elektron jadvallar bilan ishlaydi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Optimallashtirish vositalari qanday vazifalarni bajaradi?",
                    Arrays.asList(
                            "Diskga fayllarni optimal joylashtirishga xizmat qiladi",
                            "Operasion tizimning optimal ishlashini ta’minlaydi",
                            "Matnli ma’lumotlarni ekranga optimal joylashtiradi",
                            "Monitorning optimal ishlash parametrlarini o’rnatadi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Disklar formatlanganda undagi fayllar nima bo’ladi?",
                    Arrays.asList(
                            "Hammasi o’chib ketadi",
                            "Arxivlanadi",
                            "Faqat tizimli dasturlar qoladi",
                            "Zararlanmaydi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Microsoft kompaniyasi qanaqa mahsulotlari bilan mashhur?",
                    Arrays.asList(
                            "Dasturiy vositalari",
                            "Kompyuterlari",
                            "Internet tizimi vositalari",
                            "Mikroprosessarlari"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Dasturiy komplekslar joylashgan satrni ko’rsating.",
                    Arrays.asList(
                            "Norton Utilities, Norton Antivirus, Microsoft Office",
                            "MS DOS, Windows, UNIX",
                            "WinRar, DrWeb, Access",
                            "Barchasi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "EHMga tushunarli tilda yozilgan algoritm, bu …",
                    Arrays.asList(
                            "Dastur",
                            "Algoritm",
                            "Qism-dastur",
                            "Massiv"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Diagnostika vositalari qanday vazifalarni bajaradi?",
                    Arrays.asList(
                            "Qurilmalarning va magnit disk sathining nuqsonli joylarini aniqlaydi",
                            "Qurilmalarning ishlashini nazorat qiladi",
                            "Qurilmalarning nuqsonli joylarinpi aniqlaydi va tuzatadi",
                            "Virus dasturlarni aniqlaydi va tuzatadi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Fayllarning kengaytmalari nimani bildiradi?",
                    Arrays.asList(
                            "Faylning tipini",
                            "Faylni yaratgan shaxsning nomini",
                            "Faylning nomini",
                            "Faylning yaratilgan sanasini"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Dasturiy ta’minotning ishonchliligi – bu",
                    Arrays.asList(
                            "Dasturning ma’lum bir davrda xatolarsiz ishlay olish xususiyati",
                            "Dasturning uzgartirishlarga moslanganligi",
                            "Dasturning tuxtovsiz ishlay olish xususiyati",
                            "Dasturning ixtiyoriy turdagi kompyuterlarga moslanganligi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Amaliy dasturlar qanday vazifalarni bajaradi?",
                    Arrays.asList(
                            "Foydalanuvchiga kompyuterda ma’lum amallarni bajarishiga imkon beradi",
                            "Dasturlar tuzishga yordam beradi",
                            "Kompyuterning ishlashini nazorat qiladi",
                            "Fayllarni arxivlaydi va disklarni formatlaydi"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Amaliy dasturlar joylashgan qatorni ko’rsating?",
                    Arrays.asList(
                            "Microsoft Word, Microsoft Excel, Adobe Photoshop",
                            "Microsoft Word, WinRar, DrWeb",
                            "NC,VC,Windows",
                            "Windows, MS DOS, NC"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Matn muharrirlari joylashgan qatorni ko’rsating.",
                    Arrays.asList(
                            "Word, PE2, Leksikon",
                            "Word, LUX, Excel",
                            "Power Point",
                            "Word, Excel, Access"
                    ),
                    0
            ));
        }
        else if ("Variant 3".equals(variant)) {
            list.add(new TestModel(
                    "Matn muharrirlari yordamida qanday amallarni bajarish mumkin emas?",
                    Arrays.asList("Matnli hamda rasmli fayllarni yaratish", "Animasiyali fayllarni yaratish", "Hammasi mumkin emas", "Matnli fayllarni turli suratlar bilan bezash"),
                    1
            ));

            list.add(new TestModel(
                    "Elektron jadvallar qanday vazifalarni bajaradi?",
                    Arrays.asList("Kompyuter xotirasidagi fayllarni jadvallashtiradi", "FAT jadvalini tashkil qiladi", "Turli elektron jadvallarni tashkil qiladi", "Yuqoridagi barcha amallarni bajaradi"),
                    2
            ));

            list.add(new TestModel(
                    "Elektron jadvallar joylashgan satrni ko’rsating",
                    Arrays.asList("Microsoft Excel, Microsoft Word, Paradox", "Access, Boieng Graf, Super Calc", "Fox Pro, Rbase, Microsoft Excel", "Super Calc, Quadro Pro, Lotus"),
                    3
            ));

            list.add(new TestModel(
                    "Ofis-ilovalar joylashgan satrni ko’rsating",
                    Arrays.asList("Microsoft Office, Perfect Office, Norton Antivirus", "Microsoft Office, Perfect Office", "Lotus-Troyka, Microsoft Office, Perfect Office", "Nortot Utilities; Barchasi"),
                    2
            ));

            list.add(new TestModel(
                    "Maple nima?",
                    Arrays.asList("Kompyuter markasi", "Drayver nomi", "Amaliy dasturiy ta’minot", "Qurilma nomi"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi vosita yordamida turli diagrammalarni yaratish mumkin?",
                    Arrays.asList("Matn muharrirlari", "Loyihalash vositalari", "Elektron jadvallar", "Prezentasiyalar paketi"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi muharrir yordamida yaratilgan matnli fayllarning kengaytmasi .doc bo’ladi?",
                    Arrays.asList("Microsoft Excel", "Adobe Photoshop", "Microsoft Word", "EDLIN"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi vosita yordamida skanerdan kiritilgan ma’lumotlarni Word fayllariga aylantirish mumkin?",
                    Arrays.asList("Microsoft Excel", "Fine-Reader", "Quadro Pro", "Corel Draw"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi vosita yordamida nashriyot mahsulotlarini sahifalash mumkin?",
                    Arrays.asList("Reflex", "Page Maker", "Boieng Graf", "Fine-Reader"),
                    1
            ));

            list.add(new TestModel(
                    "Kompyuterning ishlash samaradorligini oshiruvchi vositalar nima deb ataladi?",
                    Arrays.asList("Diagnostika vositalari", "Operasion tizimlar", "Utilitlar", "Interfeyslar"),
                    2
            ));

            list.add(new TestModel(
                    "HTML tili nima uchun mo’ljallangan?",
                    Arrays.asList("Butun dunyo o’rgimchaklari", "Girepmatnni belgilash tiliga", "Web sahifa yaratishga", "HTML hujjati tuzilishiga"),
                    2
            ));

            list.add(new TestModel(
                    "Utilitlar joylashgan qatorni ko’rsating.",
                    Arrays.asList("Antiviruslar, diagnostika vositalari, translyatorlar", "Operasion tizimlar, translyatorlar, arxivlash vositalari", "Arxivlash, formatlash, diagnostika vositalari va antiviruslar", "Elektron jadvallar, matn va grafik muharrirlar"),
                    2
            ));

            list.add(new TestModel(
                    "Translyatorlar qanday vazifalarni bajaradi?",
                    Arrays.asList("Kompyuterni va uning qurilmalarini boshqaradi", "Ovozli ma’lumotlarni tovush to’lqinlariga aylantiradi", "Algoritmik tildagi dasturlarni mashina tiliga tarjima qiladi", "Operasion tizimning ishini boshqaradi"),
                    2
            ));

            list.add(new TestModel(
                    "Translyatorlar joylashgan qatorni ko’rsating?",
                    Arrays.asList("Delphi, C++, MS DOS, Windows", "Basic, Pascal, Delphi, C++, Maple", "DrWeb, Visual Java, WinRar, Borland C++", "MS DOS, Windows, OS/2, UNIX, NC"),
                    1
            ));

            list.add(new TestModel(
                    "Ilova-dasturlar nima?",
                    Arrays.asList("Qurilmalarning ishini nazorat qiluvchi vositalar", "Joriy operasion tizim bilan ishlashga mo’ljallangan dasturlar", "Dasturlar to’grisida ma’lumotnomalar", "Dasturlarning ishlashini tezlatuvchi vositalar"),
                    1
            ));

            list.add(new TestModel(
                    "Foydalanuvchining interfeysi nima?",
                    Arrays.asList("Foydalanuvchi bilan ishlayotgan qurilmalar", "Tizimli dasturlarning bir turi", "Utilitlarning bir turi", "Foydalanuvchi bilan kompyuter o’rtasidagi muloqatni qulaylashtiruvchi uskunaviy vositalar"),
                    3
            ));

            list.add(new TestModel(
                    "Arxivlash vositalari qaysi tipdagi fayllarning hajmini eng ko’p darajada kichraytira oladi?",
                    Arrays.asList("Tizimli fayllarning", "Ovozli fayllarning", "Matnli fayllarning", "Suratli fayllarning"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi algoritmik tilda tuzilgan dastur Windows ning ilova-dasturlari bo’la oladi?",
                    Arrays.asList("Turbo Pascal", "Visual Basic", "C++", "Java"),
                    1
            ));

            list.add(new TestModel(
                    "Makros bu",
                    Arrays.asList("hisobotdir", "so’rovlar ketma-ketligidir", "makrobuyruqlar ketma-ketligidir", "ilovalar ketma-ketligidir"),
                    2
            ));

            list.add(new TestModel(
                    "Strukturali dasturlash qanday g’oyaga asoslanadi?",
                    Arrays.asList("har qanday dasturni qismiy dasturlarga bulish mumkin", "har qanday masala bir necha qismiy masalalardan iborat", "har qanday dasturni utish operatorini bir marta ham ishlatmasdan tuzish mumkin", "har qanday dasturlash tillarida prosedura va funksiyalar tushunchalari mavjud"),
                    2
            ));

            list.add(new TestModel(
                    "HTML tili nima uchun mo’ljallangan?",
                    Arrays.asList("HTML hujjati tuzilishiga", "Web sahifa yaratishga", "Butun dunyo o’rgimchaklari", "Girepmatnni belgilash tiliga"),
                    1
            ));

            list.add(new TestModel(
                    "Ob’yektga yo’naltirilgan dasturlash kuyidagi uch tushunchaga asoslanadi:",
                    Arrays.asList("inkapsulyasiya; metodlar; polimorfizm", "proseduralar; funksiyalar; metodlar", "inkapsulyasiya; merosxurlik; polimorfizm", "inkapsulyasiya; merosxurlik (nasledovaniye); statik metodlar"),
                    2
            ));

            list.add(new TestModel(
                    "Foydalanuvchi tomonidan o’zgaruvchiga berilgan nom, bu …?",
                    Arrays.asList("Indeks", "Identifikator", "Deskriptor", "Parametr"),
                    1
            ));

            list.add(new TestModel(
                    "Tizimli dasturlar qanday guruhlarga bo’linadi?",
                    Arrays.asList("Operasion tizimlar va drayverlar", "Operasion tizimlar va operasion qobiq'lar", "Operasion tizimlar, fayl-menejerlar, operasion qobiq'lar va drayverlar", "Operasion qobiq'lar va drayverlar"),
                    2
            ));

            list.add(new TestModel(
                    "MS DOS ning matnli fayllarini ko’rsating.",
                    Arrays.asList("Io.sys, MS DOS.sys", "Windows, UNIX", "Autoexec.bat, Config.sys", "Word, Leksikon"),
                    2
            ));
        }
        else if ("Variant 4".equals(variant)) {
            list.add(new TestModel(
                    "MS DOS tizimisiz Windows ishlay oladimi?",
                    Arrays.asList("Ayrim hollarda ishlay oladi", "Umuman ishlamaydi", "Sekin ishlaydi", "Faqat o’zining fayllari bilan ishlay oladi"),
                    1
            ));

            list.add(new TestModel(
                    "MS DOS va Windows operasion tizimlari qaysi kompaniya tomonidan yaratilgan?",
                    Arrays.asList("Intel", "Microsoft", "IBM", "Novell"),
                    2
            ));

            list.add(new TestModel(
                    "Fayl-menejerlar joylashgan qatorni ko’rsating.",
                    Arrays.asList("VC, NC, PC Tools, Far", "Xtree, ProGold, WinRar", "MS DOS, Windows, NC, VC", "Word, Excel, Access"),
                    1
            ));

            list.add(new TestModel(
                    "UNIX nima?",
                    Arrays.asList("Operasion tizim", "Internet tizimi bilan ishlash vositasi", "Antivirus", "Matn muharriri"),
                    1
            ));

            list.add(new TestModel(
                    "Qaysi operasion tizim kompyuterlar to’ri bilan ishlay olmaydi?",
                    Arrays.asList("Windows NT", "Windows 95", "Windows Me", "Windows 98"),
                    2
            ));

            list.add(new TestModel(
                    "MS DOS operasion tizimining dastlabgi versiyasi qachon ishlab chiqarilgan?",
                    Arrays.asList("1986", "1981", "1980", "1991"),
                    2
            ));

            list.add(new TestModel(
                    "Windows operasion tizimining dastlabgi versiyasi qachon ishlab chiqarilgan?",
                    Arrays.asList("1998", "1995", "1997", "1991"),
                    2
            ));

            list.add(new TestModel(
                    "Qaysi operasion tizim \"uy kompyuterlari uchun eng qulay va eng ishonchli\" deb nom olgan?",
                    Arrays.asList("Windows NT", "Windows XP", "Windows 2000", "Windows 98"),
                    2
            ));

            list.add(new TestModel(
                    "MS DOS ning qaysi fayllari foydalanuvchi tomonidan tahrir qilinishi mumkin?",
                    Arrays.asList("Autoexec.bat, Config.sys", "IO.sys, MS DOS.sys", "Autoexec.bat, Command.com", "Barchasi"),
                    1
            ));

            list.add(new TestModel(
                    "Kompyuter bilan qurilmalar o’rtasidagi muloqotni o’rnatuvchi vositalar nima deb ataladi?",
                    Arrays.asList("Fayl-menejerlar", "Drayverlar", "Operasion tizimlar", "Utilitlar"),
                    2
            ));

            list.add(new TestModel(
                    "Operasion tizimlar keltirilgan qatorni ko’rsating.",
                    Arrays.asList("MS DOS, UNIX, OS/2", "Windows, MS DOS, Excel", "DrWeb, MS DOS, Windows", "Windows, MS DOS, WinRar"),
                    1
            ));

            list.add(new TestModel(
                    "MS DOS ning asosiy fayllari joylashgan satrni ko’rsating.",
                    Arrays.asList("Config.sys, Autoexec.bat, Io.sys, MS DOS.sys, Command.com", "Word, Excel, Access, NC,VC", "Bunday satr yo’q", "DrWeb, WinRar, Access, Windows, Io.sys"),
                    1
            ));

            list.add(new TestModel(
                    "Drayverlar nima?",
                    Arrays.asList("Amaliy dasturlar", "Kompyuter bilan qurilmalar o’rtasidagi muloqatni o’rnatuvchi vositalar", "Utilitlar", "Operasion tizimlarning bir turi"),
                    2
            ));

            list.add(new TestModel(
                    "FAT jadvali nima?",
                    Arrays.asList("Fayllarning diskdagi adreslarini o’zida jamlagan jadval", "O’chirilgan fayllarni vaqtincha saqlovchi vosita", "Fayllarning ro’yxatini saqlovchi jadval", "Fayllarning o’lchamlarini o’zida jamlagan jadval"),
                    1
            ));

            list.add(new TestModel(
                    "Grafik muharrirlar qanday vazifalarni bajaradi?",
                    Arrays.asList("Turli rasmli va grafik fayllarni yaratadi va ularni tahrir qiladi", "Faqat rasmlar bilan ishlaydi", "Grafik ma’lumotlarni kompyuterga kiritadi", "Fotosuratlar bilan ishlaydi"),
                    1
            ));

            list.add(new TestModel(
                    "Grafik muharrirlar joylashgan satrni ko’rsating.",
                    Arrays.asList("Adobe Illustrator, Adobe Photoshop, Corel Draw", "Adobe Photoshop, Quadro Pro, Paradox AutoCad", "Corel PhotoPaint, Quadro Pro", "Adobe Photoshop, Adobe Illustrator Paintbrus"),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasi bilan ishlovchi vositalar joylashgan satrni ko’rsating.",
                    Arrays.asList("Reflex, Access, Lotus", "Dbase, Rbase, Access", "Hammasi", "PC-File, Access, Quadro Pro"),
                    2
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasi bilan ishlovchi vositalar qanday amallarni bajara oladi?",
                    Arrays.asList("Barchasini", "Ma’lumotlar bazasini tahrir qilish", "Ma’lumotlar bazasidan kerakli ma’lumotlarni izlash", "Ma’lumotlarni saralash va bosmaga chiqarish"),
                    1
            ));

            list.add(new TestModel(
                    "Ilmiy va ish grafikasi vositalari qanday amallarni bajara oladi?",
                    Arrays.asList("Funksiyalarning qiymatlarini hisoblash", "Barchasini", "Funksiyalarning grafiklarini chizish", "Turli diagrammalarni yaratish"),
                    2
            ));

            list.add(new TestModel(
                    "Turli rasmlar bilan ishlashga mo’ljallangan dasturlar nima deb ataladi?",
                    Arrays.asList("Grafik muharrirlar", "Utilitlar", "Loyihalash vositalari", "Prezentasiyalar paketi"),
                    1
            ));

            list.add(new TestModel(
                    "Quyidagi dasturlardan qaysi biri ma’lumotlar bazasini boshqarish tizimi hisoblanadi?",
                    Arrays.asList("MS Word", "MS PowerPoint", "Paint", "MS Access"),
                    4
            ));

            list.add(new TestModel(
                    "Access so’zining ma’nosi nima?",
                    Arrays.asList("Kirish", "Bilim", "Dastur", "Ma’lumot"),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasi qaysi dasturda yaratiladi?",
                    Arrays.asList("Microsoft Access", "Adobe Photoshop", "Corel Draw", "Paint"),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini loyihalashning qaysi bosqichida ma’lumotlar bazasidagi kerakli axborotlarni izlash, saqlash, chop etish kabi ishlar bajariladi?",
                    Arrays.asList("Model sintezi", "Muammoning quyilishi", "Yaratilgan ma’lumotlar bazasi bilan ishlash", "Dasturiy uskuna yaratish"),
                    3
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini boshqarish tizimlarida jadval",
                    Arrays.asList("ma’lumotlarini saqlaydigan asosiy obyektdir", "Ishlatilmaydi", "faqat diagrammalar hosil qilish uchun tashkil etiladi", "Xususiy hollarda ishlatiladi"),
                    1
            ));
        }
        else if ("Variant 5".equals(variant)) {
            list.add(new TestModel(
                    "Ma’lumotlar bazasini boshqarish tizimlarida ma’lumotlar bazasi bu",
                    Arrays.asList("MS DOS operasion tizimida yaratilgan ma’lumotlarni Windows operasion tizimiga o’tkazish vositasidir", "fayllarni arxivlashda qo’llaniladigan yagona vositadir.", "asosiy obyektdir.", "rasmlar ishlovchi dasturlar majmuasidir."),
                    2
            ));

            list.add(new TestModel(
                    "Quyidagi javoblardan qaysi birida maydonlarga xos xususiyatlar to’g’ri keltirilgan?",
                    Arrays.asList("Saralash, izlash, tip", "Uzunlik, nom, tip.", "Yaratish, izlash, uzunlik.", "Saqlash, nom, tip."),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini boshqarish tizimlarini sinflarga ajratishda qo’llaniladigan alomatlar to’g’ri ko’rsatilgan javobni toping.",
                    Arrays.asList(
                            "Foydalaniladigan muloqat tili, yaratilgan ma’lumotlar bazalari soni, foydalanuvchilarning soni.",
                            "Ma’lumotlar bazasini boshqarish tizimlari qullaydigan ma’lumotlar modellarining darajasi, bajaradigan vazifasi, qo’llash soxasi.",
                            "Foydalaniladigan muloqat tili, ma’lumotlar modellarining darajasi, bajaradigan vazifasi, qo’llash soxasi, ishlash rejimi, qo’llaniladigan ma’lumotlar modellari.",
                            "Foydalanuvchilarning soni, yaratilgan ma’lumotlar bazalari soni"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini boshqarish tizimlarini foydalaniladigan muloqat tiliga ko’ra turkumlanishi qaysi?",
                    Arrays.asList("Yopiq va kengaytirilgan.", "Kengaytirilgan, ya’ni bir foydalanuvchili va ko’p foydalanuvchili", "Ochiq va kengaytirilgan.", "Yopiq va ochiq."),
                    3
            ));

            list.add(new TestModel(
                    "Berilgan jadvalning bitta yozuvi boshqa jadvaldagi faqat bitta yozuviga mos kelsa, bu qaysi turdagi munosabat?",
                    Arrays.asList("Birga-ko’p munosabat.", "Ko’pga-ko’p munosabat.", "Birga-bir munosabat.", "Ko’pga-bir munosabat."),
                    2
            ));

            list.add(new TestModel(
                    "Relyasion ma’lumotlar bazasida ma’lumotlar saqlaydigan asosiy obyekt nima?",
                    Arrays.asList("so’rovdir.", "moduldir.", "formadir.", "jadvaldir."),
                    3
            ));

            list.add(new TestModel(
                    "MBT da foydalanuvchiga nostandart operasiyalarni bajarish imkonini beruvchi dastur nima?",
                    Arrays.asList("So’rov.", "Forma.", "Jadval.", "Modul."),
                    3
            ));

            list.add(new TestModel(
                    "So’rovlar tuzishda ishlatiladigan to’g’ri usullar ketma-ketligini toping.",
                    Arrays.asList("A), V), G), D).", "B), V), G), D).", "A), B), V), D).", "A), B), G), D)."),
                    3
            ));

            list.add(new TestModel(
                    "«O’zgarishi mumkin bo’lgan tayyor ro’yxatdan jadvallarni yaratadi» degani MS Accessda nima?",
                    Arrays.asList("Jadvallarning importi", "Mustaqil jadvallar yaratish.", "Jadval yaratish ustasi yordamida jadvallar yaratish.", "Konstruktor yordamida jadvallar yaratish."),
                    2
            ));

            list.add(new TestModel(
                    "WinDJView formatdagi fayllarni ko’rish dasturi?",
                    Arrays.asList("Prompt", "WinZip", "WinDJView", "Adobe Acrobat Reader"),
                    2
            ));

            list.add(new TestModel(
                    "PDF formatidagi fayllarni o’qish dasturi?",
                    Arrays.asList("Adobe Acrobat Reader", "Prompt", "WinDJView", "WinZip"),
                    0
            ));

            list.add(new TestModel(
                    "Eng sodda holda ikki o’lchovli massiv yoki jadvaldan iborat bo‘lgan ma’lumotlar bazasi modeli qaysi?",
                    Arrays.asList("Semantik tarmok", "Matn.", "Iyerarxik.", "Relyasion."),
                    3
            ));

            list.add(new TestModel(
                    "Quyidagi tasdiqlardan qaysi biri noto’g’ri?",
                    Arrays.asList("Tipi OLE-nomi bilan yurituvchi maydon faqat «rost» (true) yoki «yolgon» (false) qiymatga ega bo’lishi mumkin", "Relyasion ma’lumotlar bazalari jadval tuzilmasiga ega.", "Ma’lumotlar bazalarida har qanday maydonga xos xususiyat uning uzunligidir.", "Maydon uzunligi undagi belgilar soni bilan ifodalanadi."),
                    0
            ));

            list.add(new TestModel(
                    "Quyidagi tasdiqlardan qaysi biri noto’g’ri?",
                    Arrays.asList("Sonli maydon sonli ma’lumotlarni kiritishga xizmat qiladi va undan hisob ishlarini bajarishda foydalaniladi", "Tipi «Pul birligi» bo’lgan maydonda joylashgan qiymatlar ustida arifmetik amallarni bajarib bo’lmaydi.", "Oddiy matn maydonida hisob ishlarini bajarib bo’lmaydi.", "Sana va vaqt maydoni sana va vaqtni formatlangan holda saqlash imkonini beradi"),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini loyihalashning qaysi bosqichida ma’lumotlar bazasini yaratish maqsadi va ishlash sohasi bayon etiladi?",
                    Arrays.asList("Model sintezi.", "Muammoning qo’yilishi.", "Dasturiy uskuna yaratish.", "Obyektning tahlili."),
                    1
            ));

            list.add(new TestModel(
                    "Ma’lumotlar bazasini loyihalashning qaysi bosqichida obyektlar va ularning parametrlari aniqlanadi?",
                    Arrays.asList("Obyektning tahlili.", "Dasturiy uskuna yaratish.", "Model sintezi.", "Muammoning qo’yilishi."),
                    0
            ));

            list.add(new TestModel(
                    "Qaysi ma’lumotlar bazasi modeli elementlarining bo’ysunish g’oyasiga asoslangan?",
                    Arrays.asList("Semantik tarmok.", "Relyasion.", "Iyerarxik.", "Jadval."),
                    2
            ));

            list.add(new TestModel(
                    "Kibernetika faniga kim asos solgan?",
                    Arrays.asList("I.Nyuton", "S.A.Lebedev", "N.Viner", "G.Galilev"),
                    2
            ));

            list.add(new TestModel(
                    "Zamonaviy o’qitish shakllari qanday invariant strukturaga ega?",
                    Arrays.asList(
                            "tayanch bilimlar va faoliyat usullarini faollashtirish, yangi tushuncha va faoliyat usullarini, o’quvlarni shakllantirish, bilimlarni qo’llash",
                            "bilimlarni qo’llash, tayanch bilimlar va faoliyatni faollashtirish, faoliyat o’quvlarini shakllantirish.",
                            "bilimlarni qo’llash  yangi tushuncha va faoliyat usullarini shakllantirish, o’quvlarni shakllantirish.",
                            "tayanch bilimlar va faoliyat usullarini faollashtirib, yangi tushuncha va faoliyat usullarini shakllantirish."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Kutubxona quyi tizim (elektron kutubxona) … hisoblanadi.",
                    Arrays.asList(
                            "axborot resurslarini foydalanuvchilarning vakolatiga hamda o’quv yurti tomonidan belgilanadigan shartlarga muvofiq to’plash, saqlash va taqdim qilish hisoblanadi.",
                            "barcha toifadagi foydalanuvchilarning shaxsiy yig’ma jildini yaratish va olib borish hisoblanadi.",
                            "o’quv guruhlar, mashg’ulotlar jadvali, o’quv jarayonining borishi yuzasidan nazoratni va boshqarishni shakllantirish.",
                            "namunaviy generatsiyalanadigan modullarni yaratish, foydalanuvchilarni ro’yxatga olish va ularga belgilangan huquqlarni berish, barcha asosiy modullarning o’zaro ishlashi hisoblanadi."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Kompyuterli texnologiyada bilimlar bazasi nima?",
                    Arrays.asList(
                            "informatsion tizim bo’lib, berilgan mavzu bo’yicha qo’shimcha axborotga muxtoj bo’lmagan yopiq tuzilmadir.",
                            "informatsion tizim bo’lmay, berilgan mavzu bo’yicha qo’shimcha axborotga muxtoj bo’lgan yopiq tuzilmadir.",
                            "informatsion tizim bo’lib, berilgan mavzu bo’yicha qo’shimcha axborotga muxtoj bo’lmagan yopiq tuzilmadir.",
                            "informatsion tizim bo’lmay, berilgan mavzu bo’yicha qo’shimcha axborotga muxtoj bo’lmagan yopiq tuzilmadir."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Axborot o’qitish muhiti sifatida nima tushuniladi?",
                    Arrays.asList(
                            "axborotni an’anaviy va elektron tashuvchilarga uyg’unlashtirish yo’li bilan qurilgan, virtual bibleotekalarni, taqsimlangan ma’lumotlar bazalarini, o’quv-uslubiy majmualarni va didaktikaning kengaytirilgan apparatini o’z tartibiga olgan yagona axborot-ta’lim fazoasiga aytiladi",
                            "o’quv-uslubiy majmualarni va didaktikaning kengaytirilgan apparatini o’z tartibiga olgan axborot-ta’lim muhitlariga aytiladi.",
                            "o’quvchi va o’qituvchining qobiliyatlari va ijodiy potensiallarigz bo’liq bo’lmagan axborot anturajiga aytiladi",
                            "ta’lim jarayoni sub’ekti sifatida inson bilan bog’liq bo’lmagan axborot-texnik, o’quv-uslubiy ta’limotning tizimli tartibga solingan to’plamiga aytiladi."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Mahalliy tarmoq bu?",
                    Arrays.asList("Katta hududda joylashgan abonentlarni birlashtiradi", "Kichik bir hududda joylashgan abonentlarni birlashtiradi", "Bitta respublika hududida joylashgan abonentlarni birlashtiradi", "Turli mamlakatlarda joylashgan abonentlarni birlashtiradi"),
                    1
            ));

            list.add(new TestModel(
                    "Lokal tarmoq va global tarmoqlarning farqi nimada?",
                    Arrays.asList("Global tarmoq fizik jihatdan yaqin bo‘lgan kompyuter tarmog‘i, lokal tarmoq esa uzoqda joylashgan kompyuterlar tarmog‘i", "Lokal tarmoq fizik jihatdan yaqin bo‘lgan kompyuter tarmog‘i, global tarmoq esa uzoqda joylashgan kompyuterlar tarmog‘i", "Lokal tarmoq ikkita kompyuter orasidagi tarmoq, global tarmoq esa ikkitadan ortiq kompyuterning o‘zaro aloqasi", "To‘g‘ri javob yo‘q"),
                    1
            ));

            list.add(new TestModel(
                    "Internetga qanday ulanish mumkin?",
                    Arrays.asList("Faqat telefon liniyasi orqali", "Antennalarga ulanish yo’li bilan", "telefon liniyasi, antenna yoki tarmoq orqali", "Lokal tarmoqga ulanish orqali"),
                    2
            ));

        }
        else if ("Variant 6".equals(variant)) {
            list.add(new TestModel(
                    "Elektron pochta nima?",
                    Arrays.asList(
                            "Rasmlarni uzatish va qabul qilish dasturi",
                            "Web-cahifalarni bir kompyuterdan ikkinchisiga modem orqali uzatish tizimi",
                            "Barcha javoblar to‘g‘ri",
                            "Matnlarni qabul qilish tizimi, Elektron ma’lumotlarni uzatish va qabul qilish tizimi"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Elektron pochta orqali fayllarni uzatish yo‘lini ko‘rsating.",
                    Arrays.asList(
                            "Elektron pochta orqali fayllar uzatilmaydi.",
                            "Elektron serverga kirgach, yangi ma’lumotlar yozilib, «Отправить» yoki «Send» tugmasi bosiladi",
                            "To‘g‘ri javob yo‘q",
                            "Ma’lumotlar kiritilgach, „Quit” tugmasi bosiladi"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Elektron pochta orqali uzatilgan ma’lumotlar qancha vaqtda uzatiladi?",
                    Arrays.asList(
                            "1 oyda",
                            "1 kunda",
                            "1 xaftada",
                            "Ma’lumotlar xajmiga bog‘liq"
                    ),
                    3
            ));

            list.add(new TestModel(
                    "Internetning dastlabki nomi?",
                    Arrays.asList(
                            "NSF",
                            "Arpanet",
                            "MILNET",
                            "WWW"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "Internet uchun standart qo’llaniladigan brauzer?",
                    Arrays.asList(
                            "Opera",
                            "Fire Fox",
                            "Internet Explorer",
                            "Konquerer"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Internet tarmog’iga ulangan o’rta ta’lim muassasalar ulushi (%) da",
                    Arrays.asList(
                            "2007 yil 69%, 2008 yil 78.5%",
                            "2007 yil 65%, 2008 yil 77.4%",
                            "2007 yil 67%, 2008 yil 77.5 %",
                            "2007 yil 65.4%, 2008 yil 77.5%"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Internet tarmog’iga ulangan oliy o’quv yurtlarining ulushi (%) da",
                    Arrays.asList(
                            "94%",
                            "98%",
                            "96%",
                            "100%"
                    ),
                    3
            ));

            list.add(new TestModel(
                    "ZiyoNET qanday tarmoq?",
                    Arrays.asList(
                            "ta’lim tarmog’i",
                            "elektron tarmoq",
                            "elektron-ta’ilm tarmog’i",
                            "axborot-ta’im tarmog’i"
                    ),
                    3
            ));

            list.add(new TestModel(
                    "Kompyuterli o’qitishning pedagogik amallarni mexanizatsiyalash darajasi bo’yicha maqsadi nima?",
                    Arrays.asList(
                            "tayanch bilimlarni va faoliyat usullarini faollashtirish, reproduktiv o’quv va malakalarni shakllantirish bosqichida yoppasiga o’qitish imkoniyati.",
                            "laboratoriya va amaliy ishlarni tayyorlashda o’rganuvchi ishining jadallashuvi, kompyuterning trenajyor rejimida ishlashi, kompyuter bilan ma’ruza materiali ustida, laboratoriya-amaliy mashg’ulotlarda ishlash. o’quvchilarni nazorat qilish va ularni tashxisdan o’tkazishda vaqtdan yutish, o’quvchilarga mustaqil, nazorat ishlarini berish va ularni ko’paytirishdan yutish, natijalarni qayta ishlash va ularni tezkor ravishda har bir o’rganuvchiga yetkazish va h.k.",
                            "har bir talaba kompyuterda o’zining imkoniyatlari va ishlash tezligini hisobga olgan holda ishlaydi."
                    ),
                    1
            ));

            list.add(new TestModel(
                    "O’qitishning axborot texnologiyalari –",
                    Arrays.asList(
                            "bu o’rganuvchiga axborotni tayyorlash va uzatish jarayoni bo’lib, u kompyuter vositasida amalga oshiriladi.",
                            "bu axborot texnologiyadir, chunki o’qitishning texnologik asosini axborot va uning harakati tashkil qiladi.",
                            "maxsus texnik axborot vositalaridan foydalaniladigan barcha texnologiyalar tushuniladi.",
                            "dasturlashtirilgan o’qitish g’oyalarini rivojlantiradi, o’qitishning zamonaviy kompyuterlar va telekommunikatsiyalarning imkoniyatlari bilan bog’liq bo’lgan mutlaq yangi, hali tadqiq qilinmagan o’qitishning texnologik variantlarini ochadi."
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Kompyuterli texnologiya qo’llash darajasiga ko’ra …",
                    Arrays.asList(
                            "assosiativ-reflektorli  umumpedagogik  kirib boruvchi, har qanday mazmun uchun yaroqli  kompyuterli",
                            "umumpedagogik  assosiativ-reflektorli  kirib boruvchi, har qanday mazmun uchun yaroqli kompyuterli",
                            "kirib boruvchi, har qanday mazmun uchun yaroqli kompyuterli  umumpedagogik  assosiativ-reflektorli",
                            "umumpedagogik  kirib boruvchi, har qanday mazmun uchun yaroqli kompyuterli  assosiativ-reflektorli"
                    ),
                    1
            ));
            list.add(new TestModel(
                    "G.Yu.Belyayev ta’lim muhitini necha xil usul bilan ta’riflaydi?",
                    Arrays.asList(
                            "7",
                            "6",
                            "8",
                            "5"
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Kompyuterli o’qitishning vaqt omili bo’yicha maqsadi nima?",
                    Arrays.asList(
                            "tayanch bilimlarni va faoliyat usullarini faollashtirish, reproduktiv o’quv va malakalarni shakllantirish bosqichida yoppasiga o’qitish imkoniyati.",
                            "har bir talaba kompyuterda o’zining imkoniyatlari va ishlash tezligini hisobga olgan holda ishlaydi.",
                            "laboratoriya va amaliy ishlarni tayyorlashda o’rganuvchi ishining jadallashuvi, kompyuterning trenajyor rejimida ishlashi, kompyuter bilan ma’ruza materiali ustida, laboratoriya-amaliy mashg’ulotlarda ishlash.",
                            "o’quvchilarni nazorat qilish va ularni tashxisdan o’tkazishda vaqtdan yutish, o’quvchilarga mustaqil, nazorat ishlarini berish va ularni ko’paytirishdan yutish, natijalarni qayta ishlash va ularni tezkor ravishda har bir o’rganuvchiga yetkazish va h.k."
                    ),
                    3
            ));

            list.add(new TestModel(
                    "Kompyuterli o’qitishning o’rganuvchilarga yakka holda yondashuvni qo’llash bo’yicha maqsadi nima?",
                    Arrays.asList(
                            "tayanch bilimlarni va faoliyat usullarini faollashtirish, reproduktiv o’quv va malakalarni shakllantirish bosqichida yoppasiga o’qitish imkoniyati.",
                            "o’quvchilarni nazorat qilish va ularni tashxisdan o’tkazishda vaqtdan yutish, o’quvchilarga mustaqil, nazorat ishlarini berish va ularni ko’paytirishdan yutish, natijalarni qayta ishlash va ularni tezkor ravishda har bir o’rganuvchiga yetkazish va h.k.",
                            "har bir talaba kompyuterda o’zining imkoniyatlari va ishlash tezligini hisobga olgan holda ishlaydi.",
                            "laboratoriya va amaliy ishlarni tayyorlashda o’rganuvchi ishining jadallashuvi, kompyuterning trenajyor rejimida ishlashi, kompyuter bilan ma’ruza materiali ustida, laboratoriya-amaliy mashg’ulotlarda ishlash."
                    ),
                    2
            ));

            list.add(new TestModel(
                    "Kompyuterli o’qitishning o’quv jarayoniga o’quvchilarni qamrab olish darajasi bo’yicha maqsadi nima?",
                    Arrays.asList(
                            "tayanch bilimlarni va faoliyat usullarini faollashtirish, reproduktiv o’quv va malakalarni shakllantirish bosqichida yoppasiga o’qitish imkoniyati.",
                            "o’quvchilarni nazorat qilish va ularni tashxisdan o’tkazishda vaqtdan yutish, o’quvchilarga mustaqil, nazorat ishlarini berish va ularni ko’paytirishdan yutish, natijalarni qayta ishlash va ularni tezkor ravishda har bir o’rganuvchiga yetkazish va h.k.",
                            "har bir talaba kompyuterda o’zining imkoniyatlari va ishlash tezligini hisobga olgan holda ishlaydi.",
                            "laboratoriya va amaliy ishlarni tayyorlashda o’rganuvchi ishining jadallashuvi, kompyuterning trenajyor rejimida ishlashi, kompyuter bilan ma’ruza materiali ustida, laboratoriya-amaliy mashg’ulotlarda ishlash."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "Har qanday pedagogik texnologiya –",
                    Arrays.asList(
                            "bu axborot texnologiyadir, chunki o’qitishning texnologik asosini axborot va uning harakati tashkil qiladi.",
                            "bu o’rganuvchiga axborotni tayyorlash va uzatish jarayoni bo’lib, u kompyuter vositasida amalga oshiriladi.",
                            "maxsus texnik axborot vositalaridan foydalaniladigan barcha texnologiyalar tushuniladi.",
                            "dasturlashtirilgan o’qitish g’oyalarini rivojlantiradi, o’qitishning zamonaviy kompyuterlar va telekommunikatsiyalarning imkoniyatlari bilan bog’liq bo’lgan mutlaq yangi, hali tadqiq qilinmagan o’qitishning texnologik variantlarini ochadi."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "142 Kompyuterli texnologiya mazmun tavsifiga ko’ra …",
                    Arrays.asList(
                            "umumpedagogik  assosiativ-reflektorli  kirib boruvchi, har qanday mazmun uchun yaroqli  kompyuterli",
                            "kirib boruvchi, har qanday mazmun uchun yaroqli  umumpedagogik  assosiativ-reflektorli kompyuterli",
                            "assosiativ-reflektorli  umumpedagogik  kirib boruvchi, har qanday mazmun uchun yaroqli  kompyuterli"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "143 Kompyuterli texnologiya o’zlashtirish darajasiga ko’ra …",
                    Arrays.asList(
                            "umumpedagogik  kirib boruvchi, har qanday mazmun uchun yaroqli  assosiativ-reflektorli kompyuterli",
                            "assosiativ-reflektorli  umumpedagogik  kirib boruvchi, har qanday mazmun uchun yaroqli  kompyuterli",
                            "kirib boruvchi, har qanday mazmun uchun yaroqli  kompyuterli  assosiativ-reflektorli  umumpedagogik"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "144 O’qitishda kompyuterning funksiyalari …",
                    Arrays.asList(
                            "kompyuter trenajyor, repetitor assistant sifatida, aniq vaziyatlarni modellashtiruvchi qurilma sifatida",
                            "texnik-pedagogik, didaktik",
                            "kompyuter o’quv faoliyatini jadallashtirish, o’qituvchi faoliyatini optimallashtirish"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "145 Darslik ishlab chiqish jarayonini osonlashtiruvchi dasturiy vositalar nechta?",
                    Arrays.asList(
                            "3",
                            "2",
                            "4",
                            "5"
                    ),
                    1
            ));

            list.add(new TestModel(
                    "146 Elektron o’quv kursini ishlab chiqish uchun mo’ljallangan vositalar nimalardan iborat?",
                    Arrays.asList(
                            "O’quv jarayoniga izlash-tadqiq qilish faoliyati elementlarini kiritish; evristik xarakterdagi o’quv topshiriqlarini taklif qilish; electron konferensiya rejimida ularning yechimini muhokama qilish; ilmiy tadqiqot elementlari bo’lgan laboratoriya ishlarini bajarish; ijodiy xarakterdagi jamoaviy loyihalarni bajarish",
                            "Elektron konferensiya rejimida ularning yechimini muhokama qilish ilmiy tadqiqot elementlari bo’lgan laboratoriya ishlarini bajarish ijodiy xarakterdagi jamoaviy loyihalarni bajarish"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "147 Elektron o’quv kurslarini loyihalashda qanday texnologiyalar qo’llaniladi?",
                    Arrays.asList(
                            "ma’lumotlar bazalari texnologiyalari (shu jumladan, multimediali) bilan birgalikda yuqori daraja dasturlash tilida loyihalash; gipermatnli texnologiyalar; ixtisoslashtirilgan instrumental vositalar yordamida loyihalash",
                            "gipermatnli texnologiyalar; ixtisoslashtirilgan instrumental vositalar yordamida loyihalash; ma’lumotlar bazalari texnologiyalari (shu jumladan, multimediali) bilan birgalikda yuqori daraja dasturlash tilida loyihalash; gipermatnli texnologiyalar"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "148 Elektron o’quv kursini yaratish uchun qanday tillardan foydalaniladi?",
                    Arrays.asList(
                            "HTML, JavaScript, VBscript, Perl, PHP",
                            "HTML, JavaScript, Perl, PHP",
                            "HTML, JavaScript, PHP",
                            "HTML, JavaScript, VBscript, PHP"
                    ),
                    0
            ));

            list.add(new TestModel(
                    "149 Virtual vakillik o’quvchilarga qanday imkoniyatlarni ta’minlaydi?",
                    Arrays.asList(
                            "o’quv-metodik ta’minoti joylashtirilgan elektron kutubxonadan foydalana olish; o’qituvchi bilan elektron pochta orqali muloqat qilsih; har bir o’qitiladigan kurs bo’yicha telekonferensiya; o’z virtual o’quv guruhining talabalari bilan muloqat qilish; on-line rejimda va bir qator imkoniyatlar bilan o’qituvchidan maslahat olish",
                            "o’quv-metodik ta’minoti joylashtirilgan elektron kutubxonadan foydalana olish; o’qituvchi bilan elektron pochta orqali muloqat qilsih.",
                            "har bir o’qitiladigan kurs bo’yicha telekonferensiya; o’z virtual o’quv guruhining talabalari bilan muloqat qilish",
                            "on-line rejimda va bir qator imkoniyatlar bilan o’qituvchidan maslahat olish."
                    ),
                    0
            ));

            list.add(new TestModel(
                    "150 Mediata’limni asosiy maqsadi nima?",
                    Arrays.asList(
                            "turli axborotlarni qabul qilish, uni tushunishga o’rgatish.",
                            "zamonaviy axborot sharoitlarida yangi avlodni hayotga tayyorlash",
                            "hamma javoblar to’g’ri",
                            "ruhiyatga ta’sirini anglash, texnik vositalar va zamonaviy axborot texnologiyalar yordamida kommunikatsiyalarni noverval shakllari asosida muomala usullarini o’rganish"
                    ),
                    2
            ));

        }
        return list;
    }
}