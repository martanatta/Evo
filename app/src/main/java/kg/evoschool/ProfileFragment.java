package kg.evoschool;

import static kg.evoschool.EditProfileActivity.City;
import static kg.evoschool.EditProfileActivity.Country;
import static kg.evoschool.EditProfileActivity.Name;
import static kg.evoschool.EditProfileActivity.Surname;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import kg.evoschool.AudiosAdapter;
import kg.evoschool.EditProfileActivity;
import kg.evoschool.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    AudiosAdapter musicAdapter;
    Button editProfile;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editProfile = view.findViewById(R.id.profile_edit);
        editProfile.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        TextView nameSP = view.findViewById(R.id.users_name);
        nameSP.setText(Name + " " + Surname);

        TextView citySP = view.findViewById(R.id.users_city);
        citySP.setText(City + ", " + Country);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }
}