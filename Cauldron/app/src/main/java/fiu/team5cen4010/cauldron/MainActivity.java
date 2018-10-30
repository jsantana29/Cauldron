package fiu.team5cen4010.cauldron;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import fiu.team5cen4010.cauldron.Adapter.SearchAdapter;
import fiu.team5cen4010.cauldron.Database.Database;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init View
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);

        //Init Database
        database = new Database(this);

        //Setup search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<String> suggest = new ArrayList<>();
                for(String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

                if(!enabled)
                {
                    //If close Search, just restore default
                    adapter = new SearchAdapter(getBaseContext(), database.getRecipe());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                startSearch(text.toString());
            }

            

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //Init Adapter default set all result
        adapter = new SearchAdapter(this, database.getRecipe());
        recyclerView.setAdapter(adapter);
    }

    private void startSearch(String text) {

        adapter = new SearchAdapter(this,database.getRecipeByName(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getRecipeName();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    //Once user is logged in, the back button won't close app so that it retains
    //user info until he/she decides to logout.(IMPLEMENTING THIS LATER)
    //However, removing the app from 'recents' will close app
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
