package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootStrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
         recipeRepository.saveAll(getRecipes());
    }


    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cloveUomOptional = unitOfMeasureRepository.findByDescription("Clove");

        if (!cloveUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure cloveUom = cloveUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        //get Optionals
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();

        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the " +
                "inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " + " Place in a bowl."
                + "\n" + "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole " +
                "should be a little chunky)" + "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon)" +
                " juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help " +
                "delay the avocados from turning brown.\n" + " Add the chopped onion, cilantro, black pepper, and chiles." +
                " Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add " +
                "to the guacamole to your desired degree of hotness." +"Remember that much of this is done to taste]" +
                " because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" + "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, " +
                "add it just before serving. 4 Serve: Serve immediately, or if making a few hours ahead, place plastic " +
                " wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it." +
                " (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready" +
                " to serve.)");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions," +
                "chiles, cilantro, and some chopped tomato.  Serve it as a dip at your next party or spoon it on top" +
                " of tacos for an easy dinner upgrade");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion ", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp remvoed, chopped", new BigDecimal(.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        // tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy  Grilled Chicken Tacos");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(15);
        tacoRecipe.setServings(6);
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ%22");
        tacoRecipe.setDifficulty(Difficulty.EASY);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat. \n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, " +
                "cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a " +
                "loose paste. Add the chicken to the bowl and toss to coat all over.  Set aside to marinate while the " +
                "grill heats and you prepare the rest of the toppings. \n 3 Grill the chicken: Grill the chicken for" +
                " 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat " +
                "registers 165F. Transfer to a plate and rest for 5 minutes. \n 4 Warm the tortillas: Place each" +
                " tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets " +
                "of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the " +
                "other side. \n Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n 5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of" +
                " arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle " +
                "with the thinned sour cream. Serve with lime wedges.\n");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes." +
                " Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.\n"); // this is the note
        tacoRecipe.setNotes(tacoNotes); // link the notes to the recipe
        tacoNotes.setRecipe(tacoRecipe); // link the recipe to the notes

        //chicken
        tacoRecipe.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), teaSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("cumin", new BigDecimal(1), teaSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), teaSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), teaSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), teaSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("garlic", new BigDecimal(1), cloveUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("fresh squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), tableSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(6), eachUom, tacoRecipe ));

        //To serve:
        tacoRecipe.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("medium ripe avocados, sliced", new BigDecimal(3), eachUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("radishes", new BigDecimal(4), tableSpoonUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(.5), pintUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("roughly chopped cilantro", new BigDecimal(1), eachUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(.5), cupsUom, tacoRecipe ));
        tacoRecipe.getIngredients().add(new Ingredient("lime cut into wedges", new BigDecimal(1), eachUom, tacoRecipe ));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);
        return recipes;
    }


}
