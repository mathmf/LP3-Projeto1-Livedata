package com.example.mathe_000.lp3projetolive;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mathe_000.lp3projetolive.databinding.ClienteItemBinding;



import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.List;
import java.util.Objects;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    List<? extends Cliente> mClienteList;

    @Nullable
    private final ClienteClickCallback mClienteClickCallback;

    public ClienteAdapter(@Nullable ClienteClickCallback clickCallback) {
        mClienteClickCallback = clickCallback;
    }

    public void setClienteList(final List<? extends Cliente> clienteList) {
        if (mClienteList == null) {
            mClienteList = clienteList;
            notifyItemRangeInserted(0, clienteList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return clienteList.size();
                }

                @Override
                public int getNewListSize() {
                    return clienteList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mClienteList.get(oldItemPosition).getId() ==
                            clienteList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Cliente newCliente = clienteList.get(newItemPosition);
                    Cliente oldCliente = mClienteList.get(oldItemPosition);
                    return newCliente.getId() == oldCliente.getId()
                            && (newCliente.getNome().equals(oldCliente.getNome()))
                            && (newCliente.getCPF() == oldCliente.getCPF())
                            ;
            }
            });
            mClienteList = clienteList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClienteItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.cliente_item,
                        parent, false);

        binding.setCallback(mClienteClickCallback);
        return new ClienteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        holder.binding.setCliente(mClienteList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mClienteList == null ? 0 : mClienteList.size();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {

        final ClienteItemBinding binding;

        public ClienteViewHolder(ClienteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
