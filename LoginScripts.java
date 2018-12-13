/***********************************************************************
* @author 			:		Srinivas Hippargi
* @description		: 		Scripts for Guest user Login
* @module			:		LoginScripts
* */

package com.abof.scripts;

import java.io.IOException;
import java.util.List;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.abof.library.BaseLib;
import com.abof.library.GenericLib;
import com.abof.pageobjects.BrowsePlpPdpPO;
import com.abof.pageobjects.HamburgerMenuPO;
import com.abof.pageobjects.HomePagePO;
import com.abof.pageobjects.LoginPagePO;
import com.abof.pageobjects.MyFavouritesPagePO;
import com.abof.pageobjects.MyOrdersPagePO;
import com.abof.pageobjects.ProfilePagePO;
import com.abof.pageobjects.ShoppingBagPO;
import com.abof.pageobjects.WhatsHotLandingPO;
import com.gargoylesoftware.htmlunit.javascript.host.canvas.CanvasCaptureMediaStream;
import com.kirwa.nxgreport.NXGReports;
import com.kirwa.nxgreport.logging.LogAs;
import com.kirwa.nxgreport.selenium.reports.CaptureScreen;
import com.kirwa.nxgreport.selenium.reports.CaptureScreen.ScreenshotOf;

import io.appium.java_client.android.AndroidKeyCode;

public class LoginScripts extends BaseLib {

	LoginPagePO loginPo = null;
	HomePagePO homePagePo = null;
	HamburgerMenuPO hamburgerMenuPo = null;
	ProfilePagePO profilePagePo = null;
	WhatsHotLandingPO whatsHotLandingPo = null;
	MyOrdersPagePO myOrdersPagePo = null;
	MyFavouritesPagePO myFavouritePagePo = null;
	BrowsePlpPdpPO browsePlpPdpPo = null;
	ShoppingBagPO shoppingBagPo = null;
	int count = 0;
	String sData[] = null;

	@BeforeMethod
	public void init() {
		loginPo = new LoginPagePO(driver);
		homePagePo = new HomePagePO(driver);
		hamburgerMenuPo = new HamburgerMenuPO(driver);
		profilePagePo = new ProfilePagePO(driver);
		whatsHotLandingPo = new WhatsHotLandingPO(driver);
		myOrdersPagePo = new MyOrdersPagePO(driver);
		myFavouritePagePo = new MyFavouritesPagePO(driver);
		browsePlpPdpPo = new BrowsePlpPdpPO(driver);
		shoppingBagPo = new ShoppingBagPO(driver);

	}

	/*
	 * @Description:To Check the normal Guest login functionality while adding
	 * an item to favories from PLP as Normal/FB/Gmail user and Validate show
	 * password option for login via my abof")
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Test(dataProvider = "getUsers", enabled = false, priority = 1, description = "Guest user Fav from PLP for Normal/FB/Gmail user")
	public void testGuestUserFavItemPLP(String sUser, String device) throws Exception {
		try {
			loginPo.getEleLoginCloseBtn().click();
			loginPo.handleOkayBtn();
			sData = GenericLib.toReadExcelData("Login", "TC_Search_001");
			homePagePo.searchOption(sData[3]);
			BaseLib.tapOnElement(.98, .99);
			loginPo.handleOkayBtn();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Plp");
			browsePlpPdpPo.getElePlpFavIcon().click();
			if (sUser.equalsIgnoreCase("ABOF")) {
				loginPo.verifyPasewordVisibility("TC_Login_001");
				loginPo.loginApp(sUser, "TC_Login_001");
			} else if (sUser.equalsIgnoreCase("FBLogged")) {
				loginPo.loginApp(sUser, "TC_FBLogin_001");
			} else {
				loginPo.loginApp(sUser, "TC_GmailLogin_001");
			}
			String toastMessage = BaseLib.verifyToastMessage(driver);
			Assert.assertTrue(toastMessage.contains("Cha-ching! Added to your"),
					"Cha Ching ! Added to your favourites toast message is not displayed");
			NXGReports.addStep("Cha Ching ! Added to your favourites toast message is successfully displayed",
					LogAs.PASSED, null);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description:To Check the normal Guest login functionality while adding
	 * an item to favories from PDP as a abof/FB/Gmail user , Validate show
	 * password option for login via my abof
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Test(dataProvider = "getUsers", enabled = false, priority = 2, description = "Guest user Fav from PDP for Normal/FB/Gmail userf")
	public void testGuestUserFavItemPDP(String sUser, String device) throws Exception {
		try {
			loginPo.getEleLoginCloseBtn().click();
			loginPo.handleOkayBtn();
			sData = GenericLib.toReadExcelData("Login", "TC_Search_001");
			homePagePo.searchOption(sData[2]);
			BaseLib.tapOnElement(.98, .99);
			loginPo.handleOkayBtn();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Plp");
			browsePlpPdpPo.getEleProductImageLst().get(1).click();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Pdp");
			browsePlpPdpPo.getElePdpSaveForLaterLnk().click();
			if (sUser.equalsIgnoreCase("ABOF")) {
				loginPo.verifyPasewordVisibility("TC_Login_001");
				loginPo.loginApp(sUser, "TC_Login_001");
			} else if (sUser.equalsIgnoreCase("FBLogged")) {
				loginPo.loginApp(sUser, "TC_FBLogin_001");
			} else {
				loginPo.loginApp(sUser, "TC_GmailLogin_001");
			}
			String toastMessage = BaseLib.verifyToastMessage(driver);
			Assert.assertTrue(toastMessage.contains("Cha-ching! Added to your"),
					"Cha Ching ! Added to your favourites toast message is not displayed");
			NXGReports.addStep("Cha Ching ! Added to your favourites toast message is successfully displayed",
					LogAs.PASSED, null);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description: Check whether the cart merge is working fine when guest
	 * user logging as normal/FB/Gmail user,Validate show password option for
	 * login via my abof
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Test(dataProvider = "getUsers", enabled = false, priority = 3, description = "Guest user Cart merge for Normal/FB/Gmail userf")
	public void testGuestUserCartMerge(String sUser, String device) throws Exception {
		try {
			loginPo.getEleLoginCloseBtn().click();
			loginPo.handleOkayBtn();
			sData = GenericLib.toReadExcelData("Login", "TC_Search_001");
			homePagePo.searchOption(sData[4]);
			BaseLib.tapOnElement(.98, .99);
			loginPo.handleOkayBtn();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Plp");
			browsePlpPdpPo.getEleProductImageLst().get(1).click();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Pdp");
			driver.findElement(By
					.xpath("//android.widget.LinearLayout[@resource-id='com.abof.android:id/sizeLayout']//android.widget.LinearLayout//android.widget.TextView[@index='0']"))
					.click();
			browsePlpPdpPo.getElePdpAddToBagBtn().click();
			int prevBadgeCount = Integer.parseInt(browsePlpPdpPo.getEleBadgeCount().getText());
			browsePlpPdpPo.getElePdpViewBagLnk().click();
			BaseLib.scrollToElement(5, "UP", .90, .50, shoppingBagPo.getElePlaceOrderBtn());
			shoppingBagPo.getElePlaceOrderBtn().click();
			if (sUser.equalsIgnoreCase("ABOF")) {
				loginPo.verifyPasewordVisibility("TC_Login_001");
				loginPo.loginApp(sUser, "TC_CartMerge_001");
			} else if (sUser.equalsIgnoreCase("FBLogged")) {
				loginPo.loginApp(sUser, "TC_CartMerge_001");
			} else {
				loginPo.loginApp(sUser, "TC_CartMerge_001");
			}
			BaseLib.waitForElement(shoppingBagPo.getEleDeliveryAddressTxt(), "Develery address text is displayed ",
					"Develery address text is not displayed");
			Assert.assertTrue(shoppingBagPo.getEleDeliveryAddressTxt().isDisplayed());
			driver.navigate().back();
			int currBadgeCount = Integer.parseInt(shoppingBagPo.getEleShoppingBagCount().getText());
			Assert.assertTrue(prevBadgeCount < currBadgeCount,
					"cart merge is not working fine/user doesn't have existing item");
			NXGReports.addStep("cart merge is working fine ", LogAs.PASSED, null);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description:To Verify that the user can able to return back to the
	 * Sign-up screen from T&C screen
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Parameters("device")
	@Test(enabled = false, priority = 4, description = "Verify that the user can able to return back to the Sign-up screen from T&C screen")
	public void testTermsAndConditionScreen(String device) throws Exception {
		try {
			BaseLib.elementStatus(loginPo.getEleJoinToday(), "New user? Join toady text", "displayed");
			loginPo.getEleJoinToday().click();
			BaseLib.elementStatus(loginPo.getEleSignUpTermsAndCondLnk(),
					"By joining you agree our Terms and Conditions", "displayed");
			loginPo.getEleSignUpTermsAndCondLnk().click();
			BaseLib.elementStatus(loginPo.getEleLoginCloseBtn(), "Terms and Conditions of use WebView", "displayed");
			loginPo.getEleLoginCloseBtn().click();
			BaseLib.elementStatus(loginPo.getEleSignUpJoinAbofBtn(), "Terms and Conditions of use", "displayed");
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description:Check the normal Guest login functionality while accessing
	 * the my abof option/signin option in hamburger menu
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Test(dataProvider = "getUsers", enabled = false, priority = 5, description = "Guest user Profile Name verification for Normal/FB/Gmail user")
	public void testGuestUserLoginInHamburgerMenu(String sUser, String device) throws Exception {
		try {
			loginPo.getEleLoginCloseBtn().click();
			loginPo.handleOkayBtn();
			homePagePo.getEleHamburgerMenuIcon().click();
			BaseLib.elementStatus(hamburgerMenuPo.getEleUserProfileNameTxt(), "Hi Guest ", "displayed");
			String prevUserName = hamburgerMenuPo.getEleUserProfileNameTxt().getText();
			hamburgerMenuPo.getEleUserProfileImg().click();
			if (sUser.equalsIgnoreCase("ABOF")) {
				loginPo.loginApp(sUser, "TC_Login_001");
			} else if (sUser.equalsIgnoreCase("FBLogged")) {
				loginPo.verifyPasewordVisibility("TC_FBLogin_001");
				loginPo.loginApp(sUser, "TC_FBLogin_001");
			} else {
				loginPo.loginApp(sUser, "TC_GmailLogin_001");
			}
			BaseLib.waitForElement(homePagePo.getEleHamburgerMenuIcon(), "Hamburger Menu icon is displayed",
					"Hamburger Menu icon not is displayed");
			homePagePo.getEleHamburgerMenuIcon().click();
			NXGReports.addStep(hamburgerMenuPo.getEleUserProfileNameTxt().getText(), LogAs.INFO, null);
			String currUserName = hamburgerMenuPo.getEleUserProfileNameTxt().getText();
			Assert.assertNotEquals(prevUserName, currUserName, "Guest User name is same after logging in");
			NXGReports.addStep("Guest Login is success", LogAs.PASSED, null);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description:Check the normal Guest login functionality while moving an
	 * item to favories from shopping bag as Normal/FB/Gmail user
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Test(dataProvider = "getUsers", enabled = false, priority = 6, description = "Guest user Move to Fav from cart for Noraml/FB/Gmailuser")
	public void testGuestUserMoveToFavouritesFromCart(String sUser, String device) throws Exception {
		try {
			loginPo.getEleLoginCloseBtn().click();
			loginPo.handleOkayBtn();
			sData = GenericLib.toReadExcelData("Login", "TC_Search_001");
			homePagePo.searchOption(sData[4]);
			BaseLib.tapOnElement(.98, .99);
			loginPo.handleOkayBtn();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Plp");
			browsePlpPdpPo.getEleProductImageLst().get(1).click();
			browsePlpPdpPo.toVerifyPageContentsDisplay("Pdp");
			driver.findElement(By
					.xpath("//android.widget.LinearLayout[@resource-id='com.abof.android:id/sizeLayout']//android.widget.LinearLayout//android.widget.TextView[@index='2']"))
					.click();
			browsePlpPdpPo.getElePdpAddToBagBtn().click();
			BaseLib.elementStatus(browsePlpPdpPo.getElePdpViewBagLnk(), "SuccesFully added to bag View Bag",
					"displayed");
			browsePlpPdpPo.getElePdpViewBagLnk().click();
			String prodMoveToFav = shoppingBagPo.getEleBrandName().getText();
			shoppingBagPo.getEleMoveToFavouritesIcon().click();
			NXGReports.addStep(prodMoveToFav + " prodcut is moved to favourites", LogAs.INFO, null);
			if (sUser.equalsIgnoreCase("ABOF")) {
				loginPo.loginApp(sUser, "TC_Login_001");
			} else if (sUser.equalsIgnoreCase("FBLogged")) {
				loginPo.verifyPasewordVisibility("TC_FBLogin_001");
				loginPo.loginApp(sUser, "TC_FBLogin_001");
			} else {
				loginPo.loginApp(sUser, "TC_GmailLogin_001");
			}
			if (BaseLib.verifyToastMessage(driver).equals("Cha-ching added  to your favorites")) {
				NXGReports.addStep("Cha-ching added  to your favorites", LogAs.PASSED, null);
			} else {
				NXGReports.addStep("Cha-ching added  to your favorites toast meassge is not displayed", LogAs.INFO,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			navigateBackToHomePage();
			homePagePo.getEleHamburgerMenuIcon().click();
			hamburgerMenuPo.getEleMyFavouritesLnk().click();
			String prodMovedFromCart = driver
					.findElement(By.xpath("//android.widget.TextView[@text='" + prodMoveToFav + "']")).getText();
			Assert.assertTrue(prodMoveToFav.contains(prodMovedFromCart), "the value");
			NXGReports.addStep(prodMovedFromCart + " The Favorites product text is  displayed", LogAs.PASSED, null);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * @Description:Verify Google Login functionality by adding new account
	 * 
	 * @Author: Srinivas Hippargi
	 */
	@Parameters("device")
	@Test(enabled = false, priority = 7, description = "Verify Google Login functionality by adding new account and Profile image")
	public void testAddNewGoogleAccount(String device) throws Exception {
		try {
			sData = GenericLib.toReadExcelData("Login", "TC_AddGmail_001");
			BaseLib.elementStatus(loginPo.getEleSignInGoogleLnkTab(), "Sign in with google linktab", "displayed");
			loginPo.getEleSignInGoogleLnkTab().click();
			BaseLib.elementStatus(loginPo.getEleChooseAcc("Add account"), "Add acoount screen ", "displayed");
			loginPo.getEleChooseAcc("Add account").click();
			loginPo.getEleChooseAccOKBtn().click();
			BaseLib.waitForElement(loginPo.getEleAddGmailEmailTxtBx(), "Email or Phone textbox is displayed",
					"Email or Phone textbox is not displayed");
			loginPo.getEleAddGmailEmailTxtBx().clear();
			loginPo.getEleAddGmailEmailTxtBx().sendKeys(sData[2]);
			loginPo.getEleAddGmailNextBtn().click();
			try {
				loginPo.getEleAddGmailErrorTxtBx().isDisplayed();
				NXGReports.addStep("This account already exists on your device Please add another account",
						LogAs.FAILED, null);
				throw new RuntimeException();
			} catch (RuntimeException e) {
				loginPo.getEleAddGmailPasswordTxtBx().clear();
				loginPo.getEleAddGmailPasswordTxtBx().sendKeys(sData[3]);
				loginPo.getEleAddGmailNextBtn().click();
				loginPo.getEleAddGmailAcceptBtn().click();
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
				loginPo.handleOkayBtn();
				homePagePo.getEleHamburgerMenuIcon().click();
				Assert.assertTrue(hamburgerMenuPo.getEleUserProfileImg().isDisplayed(),
						"Gmail user profile iamge is not displayed");
				NXGReports.addStep("Gmail user profile iamge is displayed", LogAs.PASSED, null);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	@Parameters("device")
	@Test(enabled = false, priority =8 , description = "Verify normal login functionality using invalid abof credentials")
	public void testInavlidLoginUser(String device) throws Exception {
		try {
			loginPo.loginApp("ABOF", "TC_Login_003");
			GenericLib.isVisible(GenericLib.path + "testInavlidLoginUser.PNG");
		} catch (Exception e) {
			throw e;
		}
	}

	@Parameters("device")
	@Test(enabled = false, priority = 9, description = "Verify Forgot Password functionality for non registered user")
	public void testForgotPasswordForNonRegisteredUser(String device) throws Exception {
		try {
			BaseLib.elementStatus(loginPo.getEleAbofForgotYourPasswordtLnk(), "Forgot your password? ", "displayed");
			loginPo.getEleAbofForgotYourPasswordtLnk().click();
			loginPo.getEleAbofForgotPasswordEmailTxtBx().sendKeys("cbtcrowd237349@gmail.com");
			loginPo.handleKeyboard();
			BaseLib.elementStatus(loginPo.getEleAbofEmailResetLinkBtn(), "Email Reset Link ", "displayed");
			loginPo.getEleAbofEmailResetLinkBtn().click();
			GenericLib.isVisible(GenericLib.path + "testForgotPasswordForNonRegisteredUser.PNG");

		} catch (Exception e) {
			throw e;
		}
	}

	@Parameters("device")
	@Test(enabled = false, priority = 10, description = "Verify the Sign-up functionality for already registered Normal/FB/G+ user")
	public void testRegisteredNormalUser(String device) throws Exception {
		try {
			loginPo.signUpUser("ABOF", "TC_SignUp_003");
			Assert.assertTrue(loginPo.getEleEmailAlreadyUseTxt().getText().contains("Ah shoot! Email already in use!"),
					"Ah shoot! Email already in use! error message is not displayed");
			NXGReports.addStep("Ah shoot! Email already in use! error message is displayed", LogAs.PASSED, null);

		} catch (Exception e) {
			throw e;
		}
	}

	@Parameters("device")
	@Test(enabled = false, priority = 11, description = "Verify FB Login functionality with and without app")
	public void testFBLogin(String device) throws Exception {
		try {
			loginPo.loginApp("FB", "TC_FBLogin_001");
			BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay got it is displayed", "Okay got it is not displayed");

		} catch (Exception e) {
			throw e;
		}
	}

	@Parameters("device")
	@Test(enabled = true, priority = 12, description = "Verify Forgot Password functionality for registered user")
	public void testForgotPasswordForRegisteredUser(String device) throws Exception {
		try {
			sData = GenericLib.toReadExcelData("Login", "TC_ForgotPassword_001");
			BaseLib.elementStatus(loginPo.getEleAbofForgotYourPasswordtLnk(), "Forgot your password? ", "displayed");
			loginPo.getEleAbofForgotYourPasswordtLnk().click();
			loginPo.getEleAbofForgotPasswordEmailTxtBx().sendKeys(sData[2]);
			loginPo.handleKeyboard();
			BaseLib.elementStatus(loginPo.getEleAbofEmailResetLinkBtn(), "Email Reset Link ", "displayed");
			loginPo.getEleAbofEmailResetLinkBtn().click();
			GenericLib.isVisible(GenericLib.path + "testForgotPassword.PNG");

			driver.startActivity("com.google.android.gm", "com.android.mail.ui.MailActivity");
			Thread.sleep(2000);
			loginPo.switchToGmailAcc(sData[2]);
			Thread.sleep(2000);
			BaseLib.swipeTopToBottm(.20, .50);
			Thread.sleep(2000);
			BaseLib.waitForElement(loginPo.getEleAbofPassResetGmailLnk(), "Abof password link mail is diplayed",
					"Abof password link mail is not diplayed");
			loginPo.getEleAbofPassResetGmailLnk().click();
			try {
				loginPo.getEleGmailResetPassLnk().isDisplayed();
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[3]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[3]);
				loginPo.getEleResetPassSubmitBtn().click();
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (NullPointerException e) {
				BaseLib.waitForElement(loginPo.getEleShowQuotedTxtLnk(), "show quoted text ", "show quoted text is not");
				loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				Thread.sleep(5000);
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[3]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[3]);
				loginPo.getEleResetPassSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (RuntimeException e) {
				/*loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				loginPo.getEleGmailResetPassLnk().click();*/
				BaseLib.waitForElement(loginPo.getEleLogonPasswordTxtBx(), "Reset Password screen is displayed",
						"Reset Password screen is not displayed");
				loginPo.getEleLogonPasswordTxtBx().sendKeys(sData[3]);
				loginPo.getEleLogonPasswordVerifyTxtBx().sendKeys(sData[3]);
				loginPo.getEleWebResetSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
						

			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Parameters("device")
	@Test(enabled = true, priority = 13, description = "Verify normal login functionality using G+/FB abof credentials")
	public void testGuestLoginForgotPassword(String device) throws Exception {
		try {
			loginPo.loginApp("ABOF", "TC_ForgotPassword_002");
			GenericLib.isVisible(GenericLib.path + "testGuestLoginForgotPassword.PNG");
			BaseLib.elementStatus(loginPo.getEleAbofForgotYourPasswordtLnk(), "Forgot your password? ", "displayed");
			loginPo.getEleAbofForgotYourPasswordtLnk().click();
			loginPo.getEleAbofForgotPasswordEmailTxtBx().sendKeys(sData[2]);
			loginPo.handleKeyboard();
			BaseLib.elementStatus(loginPo.getEleAbofEmailResetLinkBtn(), "Email Reset Link ", "displayed");
			loginPo.getEleAbofEmailResetLinkBtn().click();
			GenericLib.isVisible(GenericLib.path + "testForgotPassword.PNG");
			driver.startActivity("com.google.android.gm", "com.android.mail.ui.MailActivity");
			Thread.sleep(2000);
			loginPo.switchToGmailAcc(sData[2]);
			Thread.sleep(2000);
			BaseLib.swipeTopToBottm(.20, .50);
			Thread.sleep(2000);
			BaseLib.waitForElement(loginPo.getEleAbofPassResetGmailLnk(), "Abof password link mail is diplayed",
					"Abof password link mail is not diplayed");
			loginPo.getEleAbofPassResetGmailLnk().click();
			try {
				loginPo.getEleGmailResetPassLnk().isDisplayed();
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleResetPassSubmitBtn().click();
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (NullPointerException e) {
				loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleResetPassSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (RuntimeException e) {
				loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				loginPo.getEleGmailResetPassLnk().click();
				BaseLib.waitForElement(loginPo.getEleLogonPasswordTxtBx(), "Reset Password screen is displayed",
						"Reset Password screen is not displayed");
				loginPo.getEleLogonPasswordTxtBx().sendKeys(sData[4]);
				loginPo.getEleLogonPasswordVerifyTxtBx().sendKeys(sData[4]);
				loginPo.getEleWebResetSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");

			}
		} catch (Exception e) {
			throw e;
		}
	}

	
	@Parameters("device")
	@Test(enabled = true, priority = 14, description = "Check the Change password functionality- Normal login")
	public void testChangePassword(String device) throws Exception {
		try {
			sData = GenericLib.toReadExcelData("Login", "TC_Login_004");
			loginPo.loginApp("ABOF", "TC_Login_004");
			loginPo.handleOkayBtn();
			homePagePo.getEleHamburgerMenuIcon().click();
			hamburgerMenuPo.getEleUserProfileImg().click();
			profilePagePo.getEleChangePasswordLnk().click();
			NXGReports.addStep(profilePagePo.getEleAlertDescTxt().getText(), LogAs.PASSED, null);
			profilePagePo.getEleYesBtn().click();
			Assert.assertTrue(
					profilePagePo.getEleAlertDescTxt().getText()
							.contains("You will receive email shortly, you will be logged out now!"),
					"You will receive email shortly, you will be logged out now! alert message is not displayed");
			NXGReports.addStep("You will receive email shortly, you will be logged out now! alert message is diaplayed",
					LogAs.PASSED, null);
			profilePagePo.getEleYesBtn().click();
			driver.startActivity("com.google.android.gm", "com.android.mail.ui.MailActivity");
			Thread.sleep(2000);
			loginPo.switchToGmailAcc(sData[2]);
			Thread.sleep(2000);
			BaseLib.swipeTopToBottm(.20, .50);
			Thread.sleep(2000);
			BaseLib.waitForElement(loginPo.getEleAbofPassResetGmailLnk(), "Abof password link mail is diplayed",
					"Abof password link mail is not diplayed");
			loginPo.getEleAbofPassResetGmailLnk().click();
			try {
				loginPo.getEleGmailResetPassLnk().isDisplayed();
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleResetPassSubmitBtn().click();
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (NullPointerException e) {
				loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				loginPo.getEleGmailResetPassLnk().click();
				loginPo.getEleNewPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleConfirmPasswordTxtbx().sendKeys(sData[4]);
				loginPo.getEleResetPassSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");
			} catch (RuntimeException e) {
				loginPo.getEleShowQuotedTxtLnk().click();
				BaseLib.swipeBottomToTop(.70, .30);
				loginPo.getEleGmailResetPassLnk().click();
				BaseLib.waitForElement(loginPo.getEleLogonPasswordTxtBx(), "Reset Password screen is displayed",
						"Reset Password screen is not displayed");
				loginPo.getEleLogonPasswordTxtBx().sendKeys(sData[4]);
				loginPo.getEleLogonPasswordVerifyTxtBx().sendKeys(sData[4]);
				loginPo.getEleWebResetSubmitBtn().click();
				driver.startActivity("com.abof.android", "com.abof.android.landingpage.view.LandingPageView");
				loginPo.loginApp("ABOF", "TC_ForgotPassword_001");
				BaseLib.waitForElement(loginPo.getEleOkayBtn(), "Okay, got it button is displayed",
						"Okay, got it button is not displayed");

			}
		}

		catch (Exception e) {
			throw e;
		}
	}

	@DataProvider
	public Object[][] getUsers() {
		Object[][] user = new Object[3][2];
		String sDevice = GenericLib.getCongigValue(BaseLib.sConfigFile, "DeviceName");
		user[0][0] = "ABOF";
		user[0][1] = sDevice;
		user[1][0] = "GMAIL";
		user[1][1] = sDevice;
		user[2][0] = "FBLogged";
		user[2][1] = sDevice;
		return user;
	}

	/*
	 * @Description:
	 * 
	 * @Author:
	 * 
	 * @Parameters("device")
	 * 
	 * @Test(enabled = true,priority=9) public void testSignUpandLogout(String
	 * device) throws InterruptedException { int flag = 0;
	 * BaseLib.elementStatus(loginPo.getEleSigninRegister(), "signinRegister",
	 * "displayed"); loginPo.signUpUser("ABOF", "TC_SignUp_001");
	 * BaseLib.waitforElement(driver, loginPo.getEleOkayBtn(),
	 * " Okay Btn is not displayed"); loginPo.handleOkayBtn();
	 * BaseLib.waitforElement(driver, homePagePo.getEleHamburgerMenuIcon(),
	 * " Hamburger Menu Icon is not displayed");
	 * homePagePo.getEleHamburgerMenuIcon().click();
	 * hamburgerMenuPo.getEleUserProfileImg().click();
	 * hamburgerMenuPo.getEleLogoutBtn().click(); BaseLib.waitforElement(driver,
	 * homePagePo.getEleHamburgerMenuIcon(),
	 * " Hamburger Menu Icon is not displayed");
	 * homePagePo.getEleHamburgerMenuIcon().click(); loginPo.loginApp("ABOF",
	 * "TC_SignUp_001"); BaseLib.waitforElement(driver,
	 * whatsHotLandingPo.getEleMenModule(), " Okay Btn is not displayed");
	 * whatsHotLandingPo.getEleMenModule().click(); Thread.sleep(10000);
	 * String[] sData = GenericLib.toReadExcelData("Login", "TC_WHT_002");
	 * whatsHotLandingPo.getSubmenu(sData[2], sData[3]);
	 * loginPo.handleOkayBtn();
	 * browsePlpPdpPo.getEleProductImageLst().get(0).click();
	 * loginPo.handleOkayBtn(); sData = GenericLib.toReadExcelData("Login",
	 * "TC_PLPPDP_001"); BaseLib.waitforElement(driver,
	 * browsePlpPdpPo.getElePdpSizeGuideLnk(),
	 * " sizeGuideLink is not displayed"); browsePlpPdpPo.pdpFlow(sData[7],
	 * sData[2], "", sData[6]);// positiveValue BaseLib.scrollToElement(2,
	 * "DOWN", .20, .80, browsePlpPdpPo.getEleCartIcon());
	 * BaseLib.waitforElement(driver, browsePlpPdpPo.getElePdpAddToBagBtn(),
	 * " Add To Bag btn is not displayed");
	 * browsePlpPdpPo.getElePdpAddToBagBtn().click();
	 * BaseLib.waitforElement(driver,
	 * browsePlpPdpPo.getElePdpViewBagStatusMsg(),
	 * " View Bag Status Msg is not displayed");
	 * browsePlpPdpPo.verifyViewBagStaus(); BaseLib.waitforElement(driver,
	 * shoppingBagPo.getEleShoppingBagIcon(),
	 * " ShoppingBagIcon is not displayed");
	 * shoppingBagPo.getEleShoppingBagIcon().click();
	 * BaseLib.waitforElement(driver,
	 * shoppingBagPo.getEleShoppingBagApplycouponIcon(),
	 * " EditIcon is not displayed");
	 * shoppingBagPo.getEleShoppingBagApplycouponIcon().click();
	 * 
	 * BaseLib.scrollToElement(2, "UP", .80, .20,
	 * shoppingBagPo.getEleOtherCouponTxt()); if
	 * (shoppingBagPo.getEleOtherCouponTxt().isDisplayed()) {
	 * 
	 * List<WebElement> lst1 = driver.findElements(By.xpath(
	 * "//android.widget.TextView[contains(@text,'Other Coupons')]/../android.widget.LinearLayout[contains(@resource-id,'com.abof.android:id/other_coupon_list')]//android.widget.RelativeLayout"
	 * ));
	 * 
	 * for (int j = 0; j <= lst1.size() - 1; j++) {
	 * 
	 * if (lst1.get(j).getAttribute("clickable").equals("false")) { flag = 0; }
	 * else { flag = 1; }
	 * 
	 * } if (flag == 0) {
	 * NXGReports.addStep("Other Option list is not selectable", LogAs.PASSED,
	 * null); } else { NXGReports.addStep("Other Option list is selectable",
	 * LogAs.FAILED, null); Assert.fail(); }
	 * 
	 * }
	 * 
	 * BaseLib.scrollToElement(2, "DOWN", .20, .80,
	 * shoppingBagPo.getEleAvailableCouponTxt());
	 * shoppingBagPo.applyCoupon("valid", "list", ""); scrollToElement(2, "UP",
	 * .80, .20, shoppingBagPo.getElePlaceOrderBtn());
	 * shoppingBagPo.getElePlaceOrderBtn().click();
	 * 
	 * }
	 */
}
