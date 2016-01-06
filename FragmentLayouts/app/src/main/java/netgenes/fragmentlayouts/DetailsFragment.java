package netgenes.fragmentlayouts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Anthony on 12/29/2015.
 */
public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int index){
        // Create detailsFragment
        DetailsFragment f = new DetailsFragment();

        // Bundles are used to pass data, we are using the key named "Index"
        Bundle args = new Bundle();

        // Put what ever the index is
        args.putInt("index", index);

        // assign this key value to this fragment
        f.setArguments(args);

        return f;
    }

    // Method to get value at index
    public int getShownIndex(){
        return getArguments().getInt("index", 0);
    }

    @Nullable
    @Override
    // Layout inflater will put fragment on the screen
    // ViewGroup is the view you want to attach the fragment to
    // Bundle has the key and values
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create a new scrollView and pass it a context so it has the correct data available
        ScrollView scroller = new ScrollView(getActivity());

        TextView text = new TextView(getActivity());

        // Apply padding to our text view
        // A TypedValue is able to hold multiple dimension values that can be assigned dynamically inside of the code
        // COMPLEX_UNIT_DIP is defining the type of data we are going to use
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());

        // Set all padding values
        text.setPadding(padding, padding, padding, padding);

        scroller.addView(text);

        // Take currently selected hero's name and put their data inside
        // Call HISTORY which is the name of the array that was set up
        text.setText(SuperHeroInfo.HISTORY[getShownIndex()]);

        return scroller;
    }
}
