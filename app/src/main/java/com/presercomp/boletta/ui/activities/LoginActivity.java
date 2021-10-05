package com.presercomp.boletta.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.presercomp.boletta.R;
import com.presercomp.boletta.Utilities;
import com.presercomp.boletta.db.DBHelper;
import com.presercomp.boletta.db.User;

public class LoginActivity extends AppCompatActivity {

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context context = this;
        //El constructor hará que se cree la BD si no existe o se acutalize
        DBHelper db = new DBHelper(this);

        TextView txt_user = findViewById(R.id.txt_user);
        TextView txt_pass = findViewById(R.id.txt_pass);

        Button access = findViewById(R.id.btn_login_access);
        Button register = findViewById(R.id.btn_login_register);

        access.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String errors = "";
                errors += txt_user.getText().toString().length() == 0 ? "- Ingrese el nombre de usuario.\n" : "";
                errors += txt_pass.getText().toString().length() == 0 ? "- Ingrese la clave de acceso.\n" : "";
                if(errors.length() > 0){
                    Utilities.alert(context,"Corrija los siguiente errores:\n"+errors );
                } else {
                    User user = new User(v.getContext());
                    if(user.login(txt_user.getText().toString(), txt_pass.getText().toString())){
                        Toast.makeText(context, "Acceso Concedido", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), TicketActivity.class);
                        startActivity(intent);
                    } else{
                        Utilities.alert(context,"Usuario y/o clave incorrectos", "Acceso Denegado");
                    }
                }


            }
        });

        register.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Mensaje", Toast.LENGTH_LONG);
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}