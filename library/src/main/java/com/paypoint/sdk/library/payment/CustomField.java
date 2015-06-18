/*
 * Copyright (c) 2015. PayPoint
 */

package com.paypoint.sdk.library.payment;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.paypoint.sdk.library.exception.PaymentValidationException;

/**
 * Optional payment custom field
 */
public class CustomField {

    private static final int MAX_VALUE_LENGTH = 255;

    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private String value;

    @SerializedName("transient")
    private boolean transientFlag;

    /**
     * Set the custom field name
     * @param name field name
     * @return CustomField for chaining
     */
    public CustomField setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the custom field value
     * @param value field value
     * @return CustomField for chaining
     */
    public CustomField setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Set the custom field transient flag
     * @param transientFlag transient flag
     * @return CustomField for chaining
     */
    public CustomField setTransient(boolean transientFlag) {
        this.transientFlag = transientFlag;
        return this;
    }

    protected void validateData() throws PaymentValidationException {

        if (!TextUtils.isEmpty(value) &&
           (value.length() > MAX_VALUE_LENGTH)) {

           throw new PaymentValidationException(PaymentValidationException.ErrorCode.CUSTOM_FIELD_LENGTH_EXCEEDED);
        }
    }

    @Override
    public boolean equals(Object other) {

        if (other == null){
            return false;
        }

        if (this.getClass() != other.getClass()){
            return false;
        }

        CustomField otherCustomField = (CustomField)other;

        if (this.name == null) {
            if (otherCustomField.name != null) {
                return false;
            }
        } else {
            if (!this.name.equals(otherCustomField.name)) {
                return false;
            }
        }

        if (this.value == null) {
            if (otherCustomField.value != null) {
                return false;
            }
        } else {
            if (!this.value.equals(otherCustomField.value)) {
                return false;
            }
        }

        if (this.transientFlag != otherCustomField.transientFlag) {
            return false;
        }

        return true;
    }
}
