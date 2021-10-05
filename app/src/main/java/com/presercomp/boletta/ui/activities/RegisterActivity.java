package com.presercomp.boletta.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.presercomp.boletta.R;
import com.presercomp.boletta.databinding.ActivityRegisterBinding;
import com.presercomp.boletta.db.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Button save = findViewById(R.id.btn_register_save);
        Button cancel = findViewById(R.id.btn_register_cancel);
        TextView name = findViewById(R.id.txt_register_name);
        TextView mail = findViewById(R.id.txt_register_mail);
        TextView pass = findViewById(R.id.txt_register_pass);
        TextView rePass = findViewById(R.id.txt_register_pass_repeat);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) v.getContext();
                String errors = "";
                errors += name.getText().toString().length() == 0 ? "- Ingrese nombre del usuario.\n" : "";
                errors += mail.getText().toString().length() == 0 ? "- Ingrese correo del usuario.\n" : "";
                errors += pass.getText().toString().length() == 0 ? "- Ingrese clave de acceso.\n" : "";
                errors += !pass.getText().toString().equals(rePass.getText().toString()) ? "- Las claves ingresadas no coinciden.\n" : "";
                System.out.println(errors.length());
                if(errors.length() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
                    builder.setMessage("Corrija los errores:\n"+errors).setTitle("Error de formulario");
                    AlertDialog dialog = builder.create();
                    Toast.makeText(v.getContext(), "Corrija los errores:\n"+errors, Toast.LENGTH_LONG);
                    System.out.println("Errores...");
                } else {
                    User user = new User(v.getContext());
                    user.add(name.getText().toString(), pass.getText().toString(), mail.getText().toString());
                    long result = user.create();
                    System.out.println("Resultado de la insercion: "+result);
                    if(result > 0){
                        Toast.makeText(v.getContext(), "Usuario creado. Ingrese con sus nuevas credenciales", Toast.LENGTH_LONG);
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(v.getContext(), "Usuario no pudo ser creado.", Toast.LENGTH_LONG);
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}