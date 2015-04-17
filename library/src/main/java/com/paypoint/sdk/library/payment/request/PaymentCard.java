package com.paypoint.sdk.library.payment.request;

import com.google.gson.annotations.SerializedName;
import com.paypoint.sdk.library.exception.PaymentException;
import com.paypoint.sdk.library.utils.Cv2Utils;
import com.paypoint.sdk.library.utils.ExpiryUtils;
import com.paypoint.sdk.library.utils.PanUtils;
import com.paypoint.sdk.library.utils.StringUtils;

import java.util.Date;

/**
 * Created by HendryP on 08/04/2015.
 */
public class PaymentCard {

    @SerializedName("pan")
    private String pan;

    @SerializedName("cv2")
    private String cv2;

    @SerializedName("expiryDate")
    private String expiryDate;

    @SerializedName("cardHolderName")
    private String cardHolderName;

    public PaymentCard setPan(String pan) {
        this.pan = StringUtils.deleteWhitespace(pan);
        return this;
    }

    public PaymentCard setCv2(String cv2) {
        this.cv2 = StringUtils.deleteWhitespace(cv2);
        return this;
    }

    public PaymentCard setExpiryDate(String expiryDate) {
        this.expiryDate = StringUtils.deleteWhitespace(expiryDate);
        return this;
    }

    public PaymentCard setCardHolderName(String cardHolderName) {
        this.cardHolderName = StringUtils.deleteWhitespace(cardHolderName);
        return this;
    }

    public String getPan() {
        return pan;
    }

    public String getCv2() {
        return cv2;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void validateData() throws PaymentException {

        ExpiryUtils expiryUtils = new ExpiryUtils();

        String pan = getPan();
        String expiry = getExpiryDate();

        // check pan 15-19 digits + all numeric
        if (!PanUtils.isValidCardNumber(pan)) {
            throw new PaymentException(PaymentException.ErrorCode.CARD_PAN_INVALID);
        }

        // check luhn
        if (!PanUtils.checkLuhn(pan)) {
            throw new PaymentException(PaymentException.ErrorCode.CARD_PAN_INVALID_LUHN);
        }

        if (!expiryUtils.isValid(expiry)) {
            throw new PaymentException(PaymentException.ErrorCode.CARD_EXPIRY_INVALID);
        }

        // check expiry
        if (expiryUtils.isCardExpired(expiry, new Date())) {
            throw new PaymentException(PaymentException.ErrorCode.CARD_EXPIRED);
        }

        // check ccv
        if (!Cv2Utils.isValidCv2Number(getCv2())) {
            throw new PaymentException(PaymentException.ErrorCode.CARD_CV2_INVALID);
        }
    }
}
