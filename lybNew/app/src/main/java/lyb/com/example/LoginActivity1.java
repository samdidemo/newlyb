package lyb.com.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import lyb.MainActivity;
import lyb.R;

public class LoginActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        Button button=(Button)findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1=(EditText)findViewById(R.id.et_userName);
                EditText editText2=(EditText)findViewById(R.id.et_password);





                String name=editText1.getText().toString();
                String password=editText2.getText().toString();
                if(name.equals("linyongbin")&&password.equals("lin")){
                    Toast.makeText(LoginActivity1.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity1.this,MainActivity.class);
                    startActivity(intent);
                    LayoutInflater layoutInflater=LayoutInflater.from(LoginActivity1.this);
                    View textEntryView = layoutInflater.inflate(R.layout.nav_header, null);
                    ImageView imageView=(ImageView) textEntryView.findViewById(R.id.icon_image1);
                    imageView.setImageResource(R.drawable.oo);
                }
                else {
                    Toast.makeText(LoginActivity1.this,"登录失败，请重新输入",Toast.LENGTH_SHORT).show();
                }
               /* Intent intent=new Intent(LoginActivity1.this,MainActivity.class);
                startActivity(intent);*/
                //imageView.setImageResource(R.drawable.oo);
                //imageView.setImageDrawable(getResources().getDrawable(R.drawable.oo));


            }
        });



    }
}
