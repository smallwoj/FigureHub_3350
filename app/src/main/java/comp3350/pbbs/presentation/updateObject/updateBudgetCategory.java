package comp3350.pbbs.presentation.updateObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.Objects;
import comp3350.pbbs.R;
import comp3350.pbbs.business.AccessBudgetCategory;
import comp3350.pbbs.objects.BudgetCategory;

/**
 * updateBudgetCategory
 * Group4
 * PBBS
 *
 * This class updates(including deletes) an existed budgetCategory within the list.
 */

public class updateBudgetCategory extends AppCompatActivity implements Serializable {

	private AccessBudgetCategory accessBudgetCategory;	// AccessBudgetCategory variable
	EditText BudgetNameET;								// EditText variable for budgetName
	EditText BudgetLimitET;								// EditText variable for budgetLimit
	BudgetCategory oldBudgetCategory;                      // BudgetCategory to update

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_budget_category);
		Objects.requireNonNull(getSupportActionBar()).setTitle("Update Budget Category");
		oldBudgetCategory =
				Objects.requireNonNull((BudgetCategory) getIntent().getSerializableExtra("toModify"));


		accessBudgetCategory = new AccessBudgetCategory();
		BudgetNameET = findViewById(R.id.updateBudgetName);
		BudgetLimitET = findViewById(R.id.updateBudgetLimit);

		BudgetNameET.setText(oldBudgetCategory.getBudgetName());
		BudgetLimitET.setText(Double.toString(oldBudgetCategory.getBudgetLimit()));

		// validation for the new entered information
		findViewById(R.id.updateBudgetSubmit2).setOnClickListener(view -> {
			boolean valid = true;
			if (BudgetNameET.getText().toString().isEmpty()) {
				BudgetNameET.setError("Name required.");
				valid = false;
			}
			if (BudgetLimitET.getText().toString().isEmpty()) {
				BudgetLimitET.setError("Limit required.");
				valid = false;
			}
			if (!valid) {
				return;
			}
			if (accessBudgetCategory.updateBudgetCategory(oldBudgetCategory, BudgetNameET.getText().toString(), BudgetLimitET.getText().toString()) != null) {
				finish();
				Toast.makeText(view.getContext(), "Budget category updated!", Toast.LENGTH_SHORT).show();
			} else {
				Snackbar.make(view, "Failed to update Budget Category.", Snackbar.LENGTH_SHORT).show();
			}
		});
	}
}