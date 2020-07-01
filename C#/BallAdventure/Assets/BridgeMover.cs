using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BridgeMover : MonoBehaviour {

    public Transform destination;
    public PlayerController PlayerController;
    public float bridgeSpeed;
    //private int coinCounter;
    public bool coinsCollected;

	// Use this for initialization
	void Start () {
        coinsCollected = false;
	}
	
	// Update is called once per frame
	void Update () {
        if (coinsCollected) {
            float step = bridgeSpeed * Time.deltaTime;
            transform.position = Vector3.MoveTowards(transform.position, destination.position, step);
        }
	}

    public void MoveBridge() {
        coinsCollected = true;
    }
}
