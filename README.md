# Impoort-Rest-API!

### Local'de çalıştırmak için :
	# git clone https://github.com/canadmin/impoort-api.git
	* proje dizini içerisinde 
	# mvn spring-boot:run
# Security
>## JWT ÖZELLİĞİ EKLENDİ(Rest-api-security)
> ### özelliği aktifleştirmek için:
> impoort-api/src/main/java/com/impoort/impoortapi/security/JwtAuthFilter.java Sınıfı içerisinde 
> private boolean isProtectedUrl(HttpServletRequest request) {
>          //return !pathMatcher.match("/auth/**",request.getServletPath());
> return pathMatcher.match("/testFalan/****",request.getServletPath());
>}
>alt satırdaki return satırını yorum satırı haline alıp yukarıdaki return satırı yorum satırından almak yeterli
> özellik kayıt ve giris haric tüm servisler için header bilgisine Authorization girilmesini istiyor.


