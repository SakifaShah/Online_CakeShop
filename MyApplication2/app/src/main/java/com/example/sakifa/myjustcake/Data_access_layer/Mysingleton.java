package com.example.sakifa.myjustcake.Data_access_layer;

/**
 * Created by sakifa on 3/9/18.
 */


        import android.content.Context;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.Volley;
        import com.squareup.picasso.Cache;
        import com.squareup.picasso.Picasso;

        import java.util.concurrent.Executors;

/**
 * Created by sakifa on 3/7/18.
 */

public class Mysingleton {
    private static Mysingleton mysingleton;
    private RequestQueue requestQueue;
    private static Context mctx;
    private Mysingleton(Context context)
    {
        mctx=context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
   public static class ImageHandler
   {
       private static Picasso instance;
       public static Picasso getSharedInstance(Context context)
       {
           if(instance==null)
           {
               instance=new Picasso.Builder(context).executor(Executors.newSingleThreadExecutor()).memoryCache(Cache.NONE).build();
           }
           return instance;
       }
   }
    public static synchronized Mysingleton getInstance(Context context)
    {
        if(mysingleton==null)
        {
            mysingleton=new Mysingleton(context);
        }
        return mysingleton;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
