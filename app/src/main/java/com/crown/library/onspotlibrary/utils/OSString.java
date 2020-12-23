package com.crown.library.onspotlibrary.utils;

public class OSString {
    // order
    public static final String refOrder = "order";
    public static final String fieldOrderId = "orderId";
    public static final String fieldCustomer = "customer";
    public static final String fieldDelivery = "delivery";
    public static final String fieldItems = "items";
    public static final String fieldOrderedAt = "orderedAt";
    public static final String fieldStatusRecord = "statusRecord";
    public static final String fieldIsActiveOrder = "isActiveOrder";
    public static final String fieldIsDeliverableOrder = "isDeliverableOrder";

    // user
    public static final String refUser = "user";
    public static final String fieldUserId = "userId";
    public static final String fieldDeliveryUserId = "deliveryUserId";
    public static final String fieldDisplayName = "displayName";
    public static final String fieldProfileImageUrl = "profileImageUrl";
    public static final String fieldEmail = "email";
    public static final String fieldPhoneNumber = "phoneNumber";
    public static final String fieldLocation = "location";
    public static final String fieldRating = "rating";
    public static final String fieldBusinessOSD = "businessOSD";
    public static final String fieldDeviceTokenOSD = "deviceTokenOSD";

    // business
    public static final String refBusiness = "business";
    public static final String fieldBusiness = "business";
    public static final String fieldBusinessRefId = "businessRefId";
    public static final String fieldBusinessId = "businessId";
    public static final String fieldImageUrl = "imageUrl";
    public static final String fieldMobileNumber = "mobileNumber";
    public static final String fieldWebsite = "website";
    public static final String fieldBusinessType = "businessType";
    public static final String fieldBusinessTypes = "businessTypes";
    public static final String fieldHodAvailable = "hodAvailable";
    public static final String fieldIsOpen = "isOpen";
    public static final String fieldAdminBlocked = "adminBlocked";
    public static final String fieldIsActive = "isActive";
    public static final String fieldOwner = "owner";
    public static final String fieldDeviceToken = "deviceToken";

    // business-item
    public static final String refItem = "item";
    public static final String fieldItemId = "itemId";
    public static final String fieldArchived = "archived";
    public static final String fieldItemName = "itemName";
    public static final String fieldPrice = "price";
    public static final String fieldCategory = "category";
    public static final String fieldDescription = "description";
    public static final String fieldOnStock = "onStock";
    public static final String fieldStatus = "status";
    public static final String fieldImageUrls = "imageUrls";
    public static final String fieldIsBusinessOpen = "isBusinessOpen";
    public static final String fieldIsBusinessAdminBlocked = "isBusinessAdminBlocked";
    public static final String fieldIsBusinessActive = "isBusinessActive";

    // review
    public static final String refReview = "review";
    public static final String refReviewType = "reviewType";
    public static final String fieldCreatedOn = "createdOn";

    public static final String keyItemReview = "itemReview";
    public static final String keyDeliveryReview = "deliveryReview";

    // onspotshop
    public static final String inrSymbol = "₹";
    public static final String filter = "filter";
    public static final String data = "data";
    public static final String refOnSpotShop = "onspotshop";
    public static final String docBusinessType = "business-type";
    public static final String initiatorBusiness = "osb";
    public static final String initiatorCustomer = "os";
    public static final String initiatorDelivery = "osd";
    public static final String userLocation = "userLocation";
    public static final String refCrownOnspot = "crown-onspot";
    public static final String fieldTimeStampSecond = "_seconds";
    public static final String fieldTimeStampNanosecond = "_nanoseconds";

    // key
    public static final String keySearch = "search";
    public static final String keyInitiator = "initiator";
    public static final String keyKeywords = "keywords";

    // notification
    public static final String refNotification = "notification";
    public static final String fieldType = "type";
    public static final String fieldAccount = "account";

    // report
    public static final String refReport = "report";

    // prefixes
    public static final String preFieldOs = "os:";
    public static final String preFieldOsb = "osb:";
    public static final String preFieldOsd = "osd:";
    public static final String preFieldItem = "item:";
    public static final String preFieldOrder = "order:";
    public static final String preFieldImage = "image:";
    public static final String preFieldImages = "images:";

    // apis
    public static final String apiDomain = "https://us-central1-bicrowny-onspotshop.cloudfunctions.net";
    public static final String apiGetUser = apiDomain + "/getUser/";
    public static final String apiExplore = apiDomain + "/explore-getExplore/";
    public static final String apiGetAccountAvailability = apiDomain + "/getAccountAvailability/";
    public static final String apiGetUserAllBusiness = apiDomain + "/getUserAllBusiness/";
    public static final String apiGetUserBusiness = apiDomain + "/business-getUserBusiness/";
    public static final String apiOrderDetails = apiDomain + "/order-getDetails/";
    public static final String apiAcceptOrderDeliver = apiDomain + "/order-acceptOrderDeliver/";
    public static final String apiConfirmOrderDeliver = apiDomain + "/order-confirmOrderDelivery/";
    public static final String apiCancelOrderDeliver = apiDomain + "/order-cancelOrderDeliver/";
    public static final String apiOrderItemReviewDetails = apiDomain + "/review-getOrderCustomerReview/";
    public static final String apiCancelBusinessPartnership = apiDomain + "/business_partner-cancelBusinessPartnership/";
    public static final String apiAddBusinessRequest = apiDomain + "/business_partner-addBusinessRequest/";
    public static final String apiAcceptDPRequest = apiDomain + "/business_partner-acceptDPRequest/";
    public static final String apiRejectDPRequest = apiDomain + "/business_partner-rejectDPRequest/";

    // buckets
    public static final String bucketUserProfile = "user-profile";
    public static final String bucketBusinessProfile = "business-profile";
    public static final String bucketItemImage = "item-image";

    // in-app links
    public static final String domain = "http://onspotshop.com";
    public static final String linkProduct = domain + "/business";
    public static final String linkBusiness = domain + "/product";
    public static final String linkOrderOnline = domain + "/order-online";

    // packages
    public static final String packageOS = "com.crown.onspot";
    public static final String packageOSD = "com.crown.onspotdelivery";
    public static final String packageOSB = "com.crown.onspotbusiness";
    public static final String packageInstagram = "com.instagram.android";
    public static final String packageMap = "com.google.android.apps.maps";

    // contact
    public static final String contactInstagram = "onspotapp";
    public static final String contactGmail = "onspotbusiness@gmail.com";
}