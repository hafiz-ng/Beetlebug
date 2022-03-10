package app.beetlebug.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import app.beetlebug.FlagsOverview;
import app.beetlebug.MainActivity;
import app.beetlebug.R;
import app.beetlebug.Walkthrough;
import app.beetlebug.auth.UserSignUp;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;
    Button nextSlide;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);

        ImageView imageView = view.findViewById(R.id.imageViewSliderImage);
        ImageView ind1 = view.findViewById(R.id.indicator1);
        ImageView ind2 = view.findViewById(R.id.indicator2);
        TextView title = view.findViewById(R.id.textViewSliderTitle);
        Button gotoMain = view.findViewById(R.id.buttonSliderButton);
        Button nextSlide = view.findViewById(R.id.buttonSliderButtonNext);


        gotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UserSignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });

        nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Walkthrough.viewPager.setCurrentItem(position+1);
            }
        });



        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.walkthrough_one);
                ind1.setImageResource(R.drawable.selected_dot);
                ind2.setImageResource(R.drawable.unselected_dot);
                title.setText(R.string.onboarding_one);
                nextSlide.setVisibility(View.VISIBLE);
                nextSlide.setText("Next");
                break;
            case 1:
                imageView.setImageResource(R.drawable.walkthrough_two);
                ind1.setImageResource(R.drawable.unselected_dot);
                ind2.setImageResource(R.drawable.selected_dot);
                title.setText(R.string.onboarding_two);
                nextSlide.setVisibility(View.GONE);
                gotoMain.setText("Start Capture");
                break;
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
