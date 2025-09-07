# New Movies App 🎬

New Movies یک اپلیکیشن اندروید برای جستجو، مشاهده جزئیات و پخش فیلم‌ها با زیرنویس است، با رابط کاربری مدرن و انیمیشن‌های جذاب.

---

## 📌 Features

- جستجو و مرور فیلم‌ها
- مشاهده جزئیات فیلم‌ها و دسته‌بندی‌ها
- پخش ویدیو با زیرنویس و کنترل کامل
- پشتیبانی از حالت تمام‌صفحه و Landscape
- بررسی اتصال اینترنت و نمایش پیام مناسب
- انیمیشن‌های جذاب با Lottie
- مدیریت ناوبری با Cokoin

---

## 🛠 Technologies

- **Kotlin** با **Jetpack Compose**
- **ExoPlayer (Media3)** برای پخش ویدیو
- **Cokoin** برای DI و ناوبری
- **Lottie Compose** برای انیمیشن‌ها
- **Coroutines** برای پردازش‌های ناهمزمان
- **OkHttp & Retrofit** برای شبکه
- **MVVM Architecture** (ViewModel, Repository)
- **Coil** برای بارگذاری تصاویر
- **Android Gradle Plugin 8.x**

---

## 🏗 Architecture

- **UI:** Jetpack Compose
- **ViewModel:** مدیریت state و تعامل با Repository
- **Repository:** مدیریت داده‌های شبکه و محلی
- **Navigation:** مدیریت مسیرهای صفحات با KoinNavHost
- **Network Checker:** بررسی اتصال اینترنت
- **Animations:** نمایش انیمیشن‌های ورود و حالت‌های خالی با Lottie
- **ExoPlayer:** پخش ویدیو با کنترل کامل و حالت تمام‌صفحه

---

## 📸 Screenshots

<p align="center">
  <img src="https://user-images.githubusercontent.com/placeholder/home.png" width="280" alt="Home Screen"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/placeholder/detail.png" width="280" alt="Detail Screen"/>
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/placeholder/search.png" width="280" alt="Search Screen"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/placeholder/video.png" width="280" alt="Video Player"/>
</p>

---

## ⚠️ Notes

- اتصال اینترنت برای جستجو و پخش فیلم ضروری است.
- ویدیوها از URLs داینامیک بارگذاری می‌شوند.
- UI راست‌چین (RTL) طراحی شده است.

---

## 📝 License

MIT License © 2025
