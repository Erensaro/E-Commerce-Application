@RestController
@RequestMapping("/api")
public class EcommerceController {

    @Autowired
    private EcommerceService service;

    @GetMapping("/products")
    public List<Product> products() {
        return service.getAllProducts();
    }

    @PostMapping("/cart")
    public Cart addToCart(@RequestParam int productId, @RequestParam int qty) {
        return service.addToCart(productId, qty);
    }

    @PostMapping("/checkout")
    public Order checkout() {
        return service.checkout();
    }
}
