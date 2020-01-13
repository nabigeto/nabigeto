package com.nabigeto.gavin.nabigetonabi.Reminder_Menu;

/**
 * Created by Gavin on 7/2/2018.
 */

public class adapter_model {

    public String user_id;
    public String date;
    public String mtime;
    public String reminder_title;

    public Boolean set_active;

    public String reminder_content;

    public String reminder_position;

    public String reminder_keyposition;

    public adapter_model() {
    }

    ;

    public adapter_model(String user_id, String date, String mtime, String reminder_title, Boolean set_active, String reminder_content, String reminder_position, String reminder_keyposition) {

        this.user_id = user_id;
        this.date = date;
        this.mtime = mtime;
        this.reminder_title = reminder_title;
        this.set_active = set_active ;
        this.reminder_content = reminder_content;
        this.reminder_position = reminder_position;
        this.reminder_keyposition = reminder_keyposition;

    }

    public String getReminder_position() {
        return reminder_position;
    }

    public void setReminder_position(String reminder_position) {
        this.reminder_position = reminder_position;
    }

    public String getReminder_keyposition(){
        return reminder_keyposition;
    }

    public void setReminder_keyposition(String reminder_keyposition){
        this.reminder_keyposition = reminder_keyposition;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getReminder_title() {
        return reminder_title;
    }

    public void setReminder_title(String reminder_title) {
        this.reminder_title = reminder_title;
    }

    public Boolean getSet_active() {
        return set_active;
    }

    public void setSet_active(Boolean set_active) {
        this.set_active = set_active;
    }

    public String getReminder_content() {
        return reminder_content;
    }

    public void setReminder_content(String reminder_content) {
        this.reminder_content = reminder_content;
    }


    /**
    public String getUser_id() {
        return user_id;
    }

    public String getDate() {
        return date;
    }

    public String getMtime() {
        return mtime;
    }

    public String getReminder_title() {
        return reminder_title;
    }

    public Boolean getSet_active() {
        return set_active;
    }

    public String getReminder_content() {
        return reminder_content;
    }

    public void setUser_id(String user_id){
        user_id=user_id;

    }

    public void setDate(String mtime){
        mtime=date;
    }

    public void setReminder_title(String mreminder_title){
        mreminder_title=reminder_title;
    }

    public void setSet_active(Boolean mset_active){
        mset_active=set_active;
    }

    public void setReminder_content(String mReminder_content){
        mReminder_content=reminder_content;
    }
**/
 }


/**
public class adapter_model implements Parcelable {

    String user_name;
    String reminder_number;
    String reminder_text;
    String reminder_place;
    String reminder_time;
    String reminder_date;
    String reminder_type;

    public adapter_model(String mName, String mNumber, String mText, String mPlace, String mTime, String mDate, String mType){
        this.user_name = mName;
        this.reminder_number = mNumber;
        this.reminder_text = mText;
        this.reminder_place = mPlace;
        this.reminder_time = mTime;
        this.reminder_date = mDate;
        this.reminder_type = mType;
    }

    public int getItem(int Position){
        return Position;
    }

    public int describeContents(){
        return 0;
    }

    public adapter_model(Parcel in){
        user_name = in.readString();
        reminder_number = in.readString();
        reminder_text = in.readString();
        reminder_place = in.readString();
        reminder_time = in.readString();
        reminder_date = in.readString();
        reminder_type = in.readString();
    }

    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(user_name);
        parcel.writeString(reminder_number);
        parcel.writeString(reminder_text);
        parcel.writeString(reminder_place);
        parcel.writeString(reminder_time);
        parcel.writeString(reminder_date);
        parcel.writeString(reminder_type);
    }

    public String getName(){return user_name;}

    public String getNumber(){return reminder_number;}

    public String getText(){return reminder_text;}

    public String getPlace(){return reminder_place;}

    public String getTime(){return reminder_time;}

    public String getDate(){return reminder_date;}

    public String getType(){return reminder_type;}


    public void setName(String user_name){
        this.user_name=user_name;
    }

    public void setNumber(String reminder_number){this.reminder_number=reminder_number;}

    public void setText(String reminder_text){this.reminder_text=reminder_text;}

    public void setPlace(String reminder_place){this.reminder_place=reminder_place;}

    public void setTime(String reminder_time){this.reminder_time=reminder_time;}

    public void setDate(String reminder_date){this.reminder_date=reminder_date;}

    public void setType(String reminder_type){this.reminder_type=reminder_type;}

    public static final Creator<adapter_model> CREATOR =new Creator<adapter_model>() {
        @Override
        public adapter_model createFromParcel(Parcel parcel) {
            return new adapter_model(parcel);
        }

        @Override
        public adapter_model[] newArray(int size) {
            return new adapter_model[size];
        }
    };


}

**/