package com.example.popitka2.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class ClubContract {

    private ClubContract(){

    }
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="olympus";
    public static final String SCHEME = "content://";
    public static final String AUTORITY = "com.example.popitka2";
    public static final String PATH_MEMBERS = "members";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + AUTORITY);

    public static final class MemberEntry implements BaseColumns {
        
        public static final String TABLE_NAME = "members";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "firstname";
        public static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_SPORT = "sport";
        public static final String COLUMN_MONEY = "money";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MEMBERS);
        public static final String CONTENT_MULTIPLE_ITEMS = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTORITY + "/" + PATH_MEMBERS;
        public static final String CONTENT_SINGLE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTORITY + "/" + PATH_MEMBERS;

    }
}
