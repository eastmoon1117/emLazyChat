package com.jared.emlazychat;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jared on 16/2/22.
 */
public class ChatApplication extends Application {

    private List<Activity> activities = new LinkedList<Activity>();
    private List<Service> services = new LinkedList<Service>();
    //private Account account;
    private int pid;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public void closeApplication() {
        closeActivities();
        closeServices();
    }

    /*
    public Account getCurrentAccount() {

        return account;
    }
*/
    private void closeActivities() {
        ListIterator<Activity>iterator = activities.listIterator();
        while(iterator.hasNext()) {
            Activity activity = iterator.next();
            if(activity != null) {
                activity.finish();
            }
        }
    }

    private void closeServices() {
        ListIterator<Service>iterator = services.listIterator();
        while(iterator.hasNext()) {
            Service service = iterator.next();
            if(service != null) {
                stopService(new Intent(this, service.getClass()));
            }
        }
    }
}
