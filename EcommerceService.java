@Service
public class EcommerceService {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private OrderRepository orderRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Cart addToCart(int productId, int qty) {
        Product product = productRepo.findById(productId).get();
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(qty);
        return cartRepo.save(cart);
    }

    public Order checkout() {
        List<Cart> cartItems = cartRepo.findAll();
        double total = 0;

        for (Cart item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setTotalAmount(total);
        order.setPaymentStatus("PAID");

        cartRepo.deleteAll();
        return orderRepo.save(order);
    }
}
