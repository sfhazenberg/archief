using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotatorPoint : MonoBehaviour {

    public Transform axis;
    public float speed = 0.5f;

    private GameObject target = null;
    private Vector3 offset;

    void Update () {
        transform.RotateAround(axis.position, Vector3.up, speed);  //rotates platform

        /*if (target != null) {
            target.transform.position = transform.position + offset;    //moves player along when on platform
        }*/
    }
}
