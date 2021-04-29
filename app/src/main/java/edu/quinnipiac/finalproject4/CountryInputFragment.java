package edu.quinnipiac.finalproject4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryInputFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NavController navController = null;

    private View ownView;

    public CountryInputFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CountryInputFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CountryInputFragment newInstance(String param1, String param2) {
//        CountryInputFragment fragment = new CountryInputFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ownView = inflater.inflate(R.layout.fragment_country_input, container, false);
        return ownView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.imageView2).setBackgroundResource(R.drawable.water);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                Bundle b = new Bundle();
                b.putString(ARG_PARAM1,"'" + ((EditText) ownView.findViewById(R.id.countryInput)).getText().toString() + "'");
                b.putString(ARG_PARAM2,"blank");
                navController.navigate(R.id.action_countryInputFragment_to_statisticSelectorFragment,b);
                break;
            case R.id.button2:
                navController.navigate(R.id.action_countryInputFragment_to_favoritesFragment);
                break;
        }
    }

}