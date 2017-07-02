package com.example.nikbird.students.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikbird.students.R;

import nikpack.utils.Contacts;

/**
 * Created by nikbird on 25.06.17.
 */
public class AdapterStudentlContacts extends RecyclerView.Adapter<AdapterStudentlContacts.ContactHolder> {

    private Contacts contacts;
    private Contacts.ContactType[] contactTypes;

    /**
     *
     */
    public static class ContactHolder extends RecyclerView.ViewHolder {

        private ImageView iconImage;
        private TextView contactTypeView;

        public ContactHolder(View itemView) {
            super(itemView);

            iconImage = (ImageView) itemView.findViewById(R.id.ivContactIcon);
            contactTypeView = (TextView) itemView.findViewById(R.id.tvContactType);
        }

        public void bindContact(Contacts.ContactType contactType, String value) {
            iconImage.setImageResource(contactType.getImageIndex());
            contactTypeView.setText(value);
        }
    }

    public AdapterStudentlContacts(Contacts contacts) {
        this.contacts = contacts;
        contactTypes = contacts.getContactTypes();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvrow_student_contact, parent, false);
        return new ContactHolder(inflated);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contacts.ContactType contactType = contactTypes[position];
        holder.bindContact(contactType, contacts.get(contactType).get(0));
    }

    @Override
    public int getItemCount() {
        return contacts.getCount();
    }
}
