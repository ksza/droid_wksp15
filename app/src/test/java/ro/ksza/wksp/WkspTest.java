package ro.ksza.wksp;

import android.app.Activity;
import android.app.Application;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by karoly.szanto on 29/11/15.
 */
public class WkspTest {

    @Test
    public void sumTest() {

        int a = 1 + 2;
        assertThat(3, is(equalTo(a)));
    }

    @Test
    public void mockApplicationTest() {
        Activity activity = mock(Activity.class);
        Application app = mock(Application.class);
        when(activity.getApplication()).thenReturn(app);

        assertThat(app, is(equalTo(activity.getApplication())));

        verify(activity).getApplication();
        verifyNoMoreInteractions(activity);
    }
}
