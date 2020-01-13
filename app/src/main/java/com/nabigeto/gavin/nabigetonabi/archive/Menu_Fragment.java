package com.nabigeto.gavin.nabigetonabi.archive;


import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu_Fragment extends Fragment {

/**
    public String menu_input_speech_text;
    public TextView textView1;
    public Button button1;

    public String search;
    public String favourites;
    public String highlights;

    final private int REQ_CODE_SPEECH_INPUT = 100;


    public Menu_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_reminder__menu_, container, false);
        textView1 = (TextView) rootView.findViewById(R.id.search_text_box);
        button1 = (Button) rootView.findViewById(R.id.search_button_1);

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
                    public void onClick(View v){
                selection_menu();
            }
        }
        );

        return rootView;
    }


    private void selection_menu () {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
 /**       intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.));
        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(getActivity().getApplicationContext(), "Ooh, its not worked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ){
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    menu_input_speech_text = result.get(0);
                    textView1.setText(menu_input_speech_text);

                }
            }
        }

    }
/**
    public void menu_selection(String voice_selection){

        switch(voice_selection){

            case search:

            case favourites:

            case highlights:
        }

    }
**/
}
