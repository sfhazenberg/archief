using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ElevWallW : MonoBehaviour {

    public Transform origin;
    public Transform farEnd;
    public float speed = 3;

    private bool move = false;

    void Update() {
        float step = speed * Time.deltaTime;

        if (move) {
            transform.position = Vector3.MoveTowards(transform.position, farEnd.position, step);
        }
        else {
            transform.position = Vector3.MoveTowards(transform.position, origin.position, step);
        }
    }

    public void LowerDoor() {
        move = true;
    }

    public void RaiseDoor() {
        move = false;
    }
}
