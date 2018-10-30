package fiu.team5cen4010.cauldron.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import fiu.team5cen4010.cauldron.Model.Recipe;
import fiu.team5cen4010.cauldron.R;

class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imageid;
    public TextView name, description, cookingtime, totalcost;
    List<Recipe> recipes = new ArrayList<Recipe>();
    Context ctx;

    public SearchViewHolder(View itemView, Context ctx, List<Recipe> recipes) {
        super(itemView);
        this.ctx = ctx;
        this.recipes = recipes;
        itemView.setOnClickListener(this);
        imageid = (ImageView)itemView.findViewById(R.id.imageid);
        name = (TextView)itemView.findViewById(R.id.name);
        description = (TextView)itemView.findViewById(R.id.description);
        cookingtime = (TextView)itemView.findViewById(R.id.cookingtime);
        totalcost = (TextView)itemView.findViewById(R.id.totalcost);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        Recipe recipe = this.recipes.get(position);

        Resources res = this.ctx.getResources();
        int image_resourceId = res.getIdentifier("fiu.team5cen4010.cauldron:drawable/" + recipe.getImageid(), null, null);

        //Develop further to get nutrition image through a button inside recipeview
        int nutritionimage_resourceId = res.getIdentifier("fiu.team5cen4010.cauldron:drawable/" + recipe.getNutritionimage(), null, null);
        try {
            String ingredients = IOUtils.toString(ctx.getAssets().open(recipe.getIngredients()), StandardCharsets.UTF_8);
            String instructions = IOUtils.toString(ctx.getAssets().open(recipe.getInstruction()), StandardCharsets.UTF_8);

            Intent GOTOrecipeView = new Intent(this.ctx, RecipeView.class);
            GOTOrecipeView.putExtra("imageid", image_resourceId);
            GOTOrecipeView.putExtra("name", recipe.getName());
            GOTOrecipeView.putExtra("description", recipe.getDescription());
            GOTOrecipeView.putExtra("ingredients", ingredients);
            GOTOrecipeView.putExtra("instructions", instructions);
            GOTOrecipeView.putExtra("nutrition", nutritionimage_resourceId);
            this.ctx.startActivity(GOTOrecipeView);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public SearchAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView,context,recipes);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Resources res = this.context.getResources();
        int resourceId = res.getIdentifier("fiu.team5cen4010.cauldron:drawable/" + recipes.get(position).getImageid(), null, null);

        holder.imageid.setImageResource(resourceId);
        holder.name.setText(recipes.get(position).getName());
        holder.description.setText(recipes.get(position).getDescription());
        holder.cookingtime.setText(recipes.get(position).getCookingtimeToString());
        holder.totalcost.setText(recipes.get(position).getTotalcostToString());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
