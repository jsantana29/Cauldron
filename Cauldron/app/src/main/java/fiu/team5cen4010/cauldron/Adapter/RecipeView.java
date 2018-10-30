package fiu.team5cen4010.cauldron.Adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fiu.team5cen4010.cauldron.R;

public class RecipeView extends AppCompatActivity  {

    ImageView imageView, nutritionImage;
    TextView text_name, text_description, text_ingredients, text_instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view_layout);
        imageView = (ImageView) findViewById(R.id.recipeview_imageid);
        text_name = (TextView) findViewById(R.id.recipeview_name);
        text_description = (TextView) findViewById(R.id.recipeview_description);
        text_ingredients = (TextView) findViewById(R.id.recipeview_ingredients);
        text_instructions = (TextView) findViewById(R.id.recipeview_instructions);
        nutritionImage = (ImageView)findViewById(R.id.recipeview_nutritionimage);

        imageView.setImageResource(getIntent().getIntExtra("imageid",0));
        text_name.setText(getIntent().getStringExtra("name"));
        text_description.setText(getIntent().getStringExtra("description"));
        text_ingredients.setText(getIntent().getStringExtra("ingredients"));
        text_instructions.setText(getIntent().getStringExtra("instructions"));
        nutritionImage.setImageResource(getIntent().getIntExtra("nutrition",0));
    }

    public void onClickNutrition(View view) {

        //Resources res = this.ctx.getResources();
        //int image_resourceId = res.getIdentifier("fiu.team5cen4010.cauldron:drawable/chickenparmesannut.jpg", null, null);
        //Develop further to get nutrition image through a button inside recipeview
        //int nutritionimage_resourceId = res.getIdentifier("fiu.team5cen4010.cauldron:drawable/" + recipe.getNutritionimage(), null, null);
        //Intent GOTO_nutritionimage = new Intent(this, RecipeView.class);
        //GOTO_nutritionimage.putExtra("nutrition", image_resourceId);
        //startActivity(GOTO_nutritionimage);
    }
}
