package edu.ucla.cens.mobility;

import android.content.Intent;
import android.database.SQLException;
import android.os.IBinder;

public class GarbageCollectService extends WakefulIntentService
{
	private final static int gctime = 10; //days
	public GarbageCollectService()
	{
		super("ClassifierService");
		// TODO Auto-generated constructor stub
	}


	private static final String TAG = "GarbageCollectService";

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();

	}


	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doWakefulWork(Intent intent)
	{
		MobilityDbAdapter mda = new MobilityDbAdapter(this, "mobility", "mobility", "mobility");
		mda.open();
		try
		{
			
			mda.deleteSomeRows(System.currentTimeMillis() - gctime * 24 * 3600 * 1000);
			

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mda.close();
		stopSelf();		
	}

}
