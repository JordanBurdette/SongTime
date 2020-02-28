# Running SongTime Android App


#### Three Options - Mobile device, Emulator, or Android Studio

### APK on Mobile Device
1. Connect any Android device to your PC or Mac.

2. Transfer the SongTime.apk file to your Android device.

3. Select the SongTime.apk file on your device, and follow the on screen prompts to install. (This may require you enabling installation of apps from unverified sources)

4. If you encounter any issues, please refer to this tutorial: 
   https://www.wikihow.tech/Install-APK-Files-on-Android
   
   
### APK on Android Emulator
1. Launch your Android emulator on Mac or PC.
If you don't have one, one there are a couple different options to set one up:
      
      a. Through Android Studio: https://developer.android.com/studio/run/emulator#runningapp
      
      b. Through Command Line: https://medium.com/@rishii.kumar.chawda/install-android-emulator-for-react-native-app-without-installing-android-studio-727d7734528
      
      For both methods, I would recommend using the settings mentioned below in Step 4 of "APK on Emulator via Android Studio" for setup of your emulator.

2. Transfer the SongTime.apk file to your emulator (via command line or drag & drop).

3. Select the SongTime.apk file on your device, and follow the on screen prompts to install. (This may require you enabling installation of apps from unverified sources)

4. If you encounter any issues, please refer to this tutorial: 
   https://www.wikihow.tech/Install-APK-Files-on-Android


### APK on Emulator via Android Studio

1. Ensure you have Android Studio Installed (I used v3.4.2)

2. On the Android Studio Home Screen Select “Profile or debug APK”.

3. Select the SongTime.apk.

4. If you have an emulator installed, skip to step 5.

      a. If you do not have an emulator installed, select “Tools” at the top of Android Studio.

      b. Select “AVD Manager”.

      c. Select “+ Create Virtual Device”. 
      I recommend selecting “Pixel 2”, although this may not be supported for all CPUs.

      d. Click “Next”.

      e. Click “Download” beside “Q”.

      f. Accept the terms, wait for the download to finish, and click “Finish”.

      g. Select “Q” and click “Next”.

      h. Name your emulator and click “Finish”.

5. On the main Android Studio screen. At the top right toolbar, click the green Play button. 

6. For your target deployment options, select your emulator.

7. If you encounter any issues, please refer to this alternative option using the command line here:
  https://stackoverflow.com/questions/17167636/how-to-install-an-apk-on-the-emulator-in-android-studio




