package com.example.demo.config;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository repo;

    public DataLoader(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repo.count() == 0) {

            repo.save(new Producto("Brownie sin gluten",
                    "Delicioso brownie artesanal con cacao puro, apto para celíacos y dietas especiales.",
                    4000, "/imagen/brownies-sin-gluten.jpg"));

            repo.save(new Producto("Cheesecake Sin Azúcar",
                    "Suave y cremoso cheesecake, perfecto para disfrutar sin culpa.",
                    47000, "/imagen/cheese-cake.jpg"));

            repo.save(new Producto("Galletas veganas",
                    "Crujientes galletas veganas con chips de chocolate.",
                    4500, "/imagen/galletas-avena-veganas.jpg"));

            repo.save(new Producto("Mousse de chocolate",
                    "Exquisito mousse de chocolate con capas de bizcocho y crocante.",
                    5000, "/imagen/mousse-chocolate.jpg"));

            repo.save(new Producto("Torta de Fruta",
                    "Bizcocho de vainilla con frutas frescas y crema chantilly.",
                    50000, "/imagen/Torta-Fruta.jpg"));

            repo.save(new Producto("Empanadas de Manzana",
                    "Masa hojaldrada rellena de manzana dulce.",
                    3000, "/imagen/empanadas-manzana.jpg"));

            repo.save(new Producto("Torta Vegana de Chocolate",
                    "Torta vegana de chocolate, rellena y decorada con arándanos.",
                    50000, "/imagen/torta-vegana-chocolate-arandanos.jpg"));

            repo.save(new Producto("Pan sin gluten",
                    "Hecho con harina de arroz integral, suave y sabroso.",
                    3500, "/imagen/pan-sin-gluten.jpg"));

            repo.save(new Producto("Tarta de Santiago",
                    "Postre gallego de almendra, esponjoso y aromático.",
                    6000, "/imagen/tarta-de-santiago.jpg"));

            repo.save(new Producto("Torta sin azúcar de Naranja",
                    "Torta ligera endulzada naturalmente, ideal para opciones saludables.",
                    48000, "/imagen/Torta-Sin-Azúcar-de-Naranja.webp"));

            repo.save(new Producto("Tiramisú Clásico",
                    "Postre italiano individual con capas de café, mascarpone y cacao.",
                    5500, "/imagen/Tiramisu-clasico.jpg"));

            repo.save(new Producto("Torta Chocolate",
                    "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas.",
                    45000, "/imagen/Torta-Cuadrada.png"));

            // 🔥 ESTA ERA LA QUE TE FALTABA Y TE ROMPÍA TODO
            repo.save(new Producto("Torta de vainilla",
                    "Bizcocho de vainilla con crema pastelera y glaseado.",
                    40000, "/imagen/Torta-de-vainilla.jpg"));

            repo.save(new Producto("Torta Manjar",
                    "Torta tradicional chilena con manjar y nueces.",
                    42000, "/imagen/Torta-manjar.jpg"));

            repo.save(new Producto("Torta Especial de Cumpleaños",
                    "Personalizable para ocasiones especiales.",
                    55000, "/imagen/Torta-especial.jpg"));

            repo.save(new Producto("Torta de Boda",
                    "Elegante torta diseñada para bodas.",
                    60000, "/imagen/Torta-Boda.jpg"));
        }
    }
}
