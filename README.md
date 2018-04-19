# soap-api-request

Simple library to consume SOAP APIs using ksoap2

How to Install?

1. Add Following lines in you Project Level Gradle

allprojects{

    repositories {
    
    maven{url 'https://jitpack.io'}
    
        maven { url 'https://oss.sonatype.org/content/repositories/ksoap2-android-releases/' }

    }
}

2. Add Following lines in you Application Level Gradle

dependencies {

   ...
   
    compile 'com.github.sadiqsiddiqui:soap-api-request:v1.0.0'
    compile 'com.google.code.ksoap2-android:ksoap2-android:3.1.1'
}

How to USE:

Create an object of  SOAPAPICallAsyncTask provide the parameters and get your response under success method in case of Failure error method is called.

Thanks to ksoap2. 
