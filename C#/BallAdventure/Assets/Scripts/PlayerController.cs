using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {

    public BridgeMover BridgeMover;

    public float speed;
    private Rigidbody rb;
    public Vector3 respawn = new Vector3(0, 1, 0);
    public static int coinCount;
    public int allCoins;
    public Text scoreText;
    public Transform PlayerMover;
    
	void Start() {
        rb = GetComponent<Rigidbody>();
        coinCount = 0;
        SetScoreText();
        allCoins = GameObject.FindGameObjectsWithTag("Coin").Length;
	}

    void FixedUpdate() {        //handles movement of player
        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");
        Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);
        rb.AddForce(movement * speed);
	}

    void OnTriggerEnter(Collider other) {
        if (other.gameObject.CompareTag("Coin")) {
            other.gameObject.SetActive(false);
            coinCount++;
            SetScoreText();
            print(coinCount);
            if(coinCount == allCoins) {     //ideally runs only in lvl with bridge. Lvl3 in this case.
                MoveBridge();
            }
        }
    }

    void SetScoreText() {
        scoreText.text = "Score: " + coinCount.ToString();
    }

    void MoveBridge() {
        print("All coins collected");
        BridgeMover.MoveBridge();
    }

    public void Respawning() {
        transform.position = respawn;       //returns player to origin
        rb.velocity = Vector3.zero;         //resets velocity, preventing addedforce to carry over
        rb.angularVelocity = Vector3.zero;  //resets velocity, preventing addedforce to carry over
    }

    private void OnCollisionEnter(Collision collision) {
        if(collision.gameObject.name == "PlayerMover") {
            transform.parent = collision.transform;     //makes player child of elevator, smooths out player movement when object moves
        }
    }

    private void OnCollisionExit(Collision collision) {
        if(collision.gameObject.name == "PlayerMover") {
            transform.parent = null;        //unhooks player from object
        }
    }
}
